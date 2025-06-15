package com.appcenter.wnt.domain.store;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "store_images")
@NoArgsConstructor
public class StoreImage {
    @Id
    @GeneratedValue
    @Column(name = "store_image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_path",nullable = false)
    private String filePath;

    @Builder
    public StoreImage(Store store, String fileName, String filePath) {
        this.store = store;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public static StoreImage of(Store store, String fileName, String filePath) {
        return StoreImage.builder().store(store).fileName(fileName).filePath(filePath).build();
    }
}
