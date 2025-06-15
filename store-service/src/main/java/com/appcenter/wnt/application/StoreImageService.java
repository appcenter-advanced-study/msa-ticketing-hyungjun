package com.appcenter.wnt.application;

import com.appcenter.wnt.domain.store.Store;
import com.appcenter.wnt.domain.store.StoreImage;
import com.appcenter.wnt.domain.store.StoreImageRepository;
import com.appcenter.wnt.domain.store.StoreRepository;
import com.appcenter.wnt.storage.FileUploadResult;
import com.appcenter.wnt.storage.FilesStorageService;
import com.appcenter.wnt.storage.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class StoreImageService {

    private StoreImageRepository storeImageRepository;
    private FilesStorageService filesStorageService;
    private StoreRepository storeRepository;

    public StoreImageService(@Value("${imagePath}") String imagePath, StoreImageRepository storeImageRepository, StoreRepository storeRepository) {
        this.storeImageRepository = storeImageRepository;
        filesStorageService = new FilesStorageService(Path.of(imagePath));
        this.storeRepository = storeRepository;
    }

    public List<StoreImage> storeImages(Long storeId, List<MultipartFile> images) {
        Store store = storeRepository.findById(storeId).orElseThrow(()-> new RuntimeException("문제발생"));
        // 1) MultipartFile → UploadFile 변환
        List<UploadFile> uploadFiles = images.stream()
                .map(mf -> {
                    try {
                        return new UploadFile(
                                mf.getInputStream(),
                                mf.getOriginalFilename()
                        );
                    } catch (IOException e) {
                        throw new UncheckedIOException("이미지 읽기 실패: " + mf.getOriginalFilename(), e);
                    }
                })
                .collect(Collectors.toList());

        // 2) common 모듈의 파일 저장 서비스 호출
        List<FileUploadResult> fileUploadResults = filesStorageService.uploadAll(uploadFiles);

        // 3) FileUploadResult → ImageResponse 매핑
        List<StoreImage> storeImages = fileUploadResults.stream().map(f->{
            return StoreImage.of(store,f.savedName(),f.url());
        }).toList();

        storeImageRepository.saveAll(storeImages);
        return storeImages;
    }
}
