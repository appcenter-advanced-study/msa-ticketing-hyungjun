package com.appcenter.wnt.storage;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FilesStorageService {

    private final Path imagePath;

    public FilesStorageService(Path imagePath) {
        this.imagePath = imagePath;
    }

    public FileUploadResult upload(UploadFile file) {
        String originalName = file.originalName();
        // 확장자 추출
        String ext = "";
        int idx = originalName.lastIndexOf('.');
        if(idx >= 0){
            ext = originalName.substring(idx);
        }
        // 저장할 파일명
        String savedName = UUID.randomUUID() + ext;
        Path target = imagePath.resolve(savedName);

        try{
            try(InputStream in = file.stream()){
                Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
            }
        }catch (IOException e){
            throw new RuntimeException("파일 저장 실패: " + originalName, e);
        }

        String url = imagePath + "/" + savedName;

        return new FileUploadResult(url, savedName);
    }

    public List<FileUploadResult> uploadAll(List<UploadFile> files) {
        return files.stream()
                .map(this::upload)
                .collect(Collectors.toList());
    }
}
