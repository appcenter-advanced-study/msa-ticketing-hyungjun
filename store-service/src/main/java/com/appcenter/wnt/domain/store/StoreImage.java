package com.appcenter.wnt.domain.store;

import com.appcenter.wnt.domain.menu.MenuItem;
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
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menu;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_path",nullable = false)
    private String filePath;

    @Builder
    public StoreImage(MenuItem menu, String fileName, String filePath) {
        this.menu = menu;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public static StoreImage of(MenuItem menu, String fileName, String filePath) {
        return StoreImage.builder().menu(menu).fileName(fileName).filePath(filePath).build();
    }
}
