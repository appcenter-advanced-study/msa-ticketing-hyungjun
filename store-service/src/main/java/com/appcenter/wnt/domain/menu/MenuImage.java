package com.appcenter.wnt.domain.menu;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class MenuImage {
    @Column(name = "file_name", nullable = false)
    private String fileName;
    @Column(name = "file_path",nullable = false)
    private String filePath;

    public MenuImage(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }
}
