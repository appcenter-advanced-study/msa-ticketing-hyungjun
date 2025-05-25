package com.appcenter.wnt.application.dto.request;

import com.appcenter.wnt.domain.menu.MenuImage;
import com.appcenter.wnt.domain.menu.MenuItem;
import com.appcenter.wnt.domain.store.Store;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record CreateMenuRequest(
        Long storeId,

        String menuName,

        int price,

        boolean representative,

        Set<String> details,

        MenuImageDto menuImage
) {
    public MenuItem toEntity(Store store) {
        return MenuItem.of(
                store,
                menuName,
                price,
                representative,
                details,
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
