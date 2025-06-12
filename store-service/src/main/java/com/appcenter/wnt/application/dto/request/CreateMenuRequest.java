package com.appcenter.wnt.application.dto.request;

import com.appcenter.wnt.domain.menu.MenuImage;
import com.appcenter.wnt.domain.menu.MenuItem;
import com.appcenter.wnt.domain.store.Store;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashSet;
import java.util.List;

public record CreateMenuRequest(

        @Schema(example = "1")
        Long storeId,
        @Schema(example = "네일 케어")
        String menuName,
        @Schema(example = "30000")
        int price,
        @Schema(example = "true")
        boolean representative,

        @Schema(example = "[\"풀컬러\", \"비회원가 기준\"]")
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
            @Schema(example = "menu.png")
            String fileName,
            @Schema(example = "C://example/wnt/menu.png")
            String filePath
    ) {
        public MenuImage toEntity() {
            return new MenuImage(fileName, filePath);
        }
    }
}
