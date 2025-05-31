package com.appcenter.wnt.application.dto.request;

import com.appcenter.wnt.domain.menu.MenuImage;
import com.appcenter.wnt.domain.menu.MenuItem;
import com.appcenter.wnt.domain.store.Store;

import java.util.HashSet;
import java.util.List;

public record CreateMenuRequest(
        Long storeId,

        String menuName,

        int price,

        boolean representative,

        List<String> details,

        MenuImageDto menuImage
) {
    public MenuItem toEntity(Store store) {
        return MenuItem.of(
                store,
                menuName,
                price,
                representative,
                new HashSet<>(details),
                menuImage.toEntity()
        );
    }

    public record MenuImageDto(
            String fileName,
            String filePath
    ) {
        public MenuImage toEntity() {
            return new MenuImage(fileName, filePath);
        }
    }
}
