package com.appcenter.wnt.application.dto.response;

import com.appcenter.wnt.domain.menu.MenuItem;
import lombok.Builder;

@Builder
public record MenuItemResponse(
        Long id,
        String menuName,
        int price
) {
    public static MenuItemResponse from(MenuItem menuItem) {
        return MenuItemResponse.builder()
                .id(menuItem.getId())
                .menuName(menuItem.getMenuName().getMenuName())
                .price(menuItem.getPrice().getPrice())
                .build();
    }
}
