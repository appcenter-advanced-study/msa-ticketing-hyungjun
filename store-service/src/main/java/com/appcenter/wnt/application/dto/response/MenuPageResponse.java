package com.appcenter.wnt.application.dto.response;

import com.appcenter.wnt.domain.menu.MenuItem;
import lombok.Builder;

import java.util.List;

@Builder
public record MenuPageResponse(
        List<MenuItemResponse> menuItemResponses
){
    @Builder
    public record MenuItemResponse(
            Long id,
            String menuName,
            int price,
            boolean representative,
            String filePath
    ) {
        public static MenuItemResponse from(MenuItem menuItem) {
            return MenuItemResponse.builder()
                    .id(menuItem.getId())
                    .menuName(menuItem.getMenuName().getMenuName())
                    .price(menuItem.getPrice().getPrice())
                    .representative(menuItem.isRepresentative())
                    .filePath(menuItem.getMenuImage().getFilePath())
                    .build();
        }
    }
    public static MenuPageResponse from(List<MenuItemResponse> menuItemResponses) {
        return MenuPageResponse.builder()
                .menuItemResponses(menuItemResponses)
                .build();
    }
}
