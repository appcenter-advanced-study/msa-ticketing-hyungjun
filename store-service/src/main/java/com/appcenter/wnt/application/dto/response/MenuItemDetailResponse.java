package com.appcenter.wnt.application.dto.response;

import com.appcenter.wnt.domain.menu.MenuItem;
import lombok.Builder;

import java.util.List;

@Builder
public record MenuItemDetailResponse(
        Long id,
        String menuName,
        int price,
        List<String> details
) {
    public static MenuItemDetailResponse from(MenuItem menuItem) {
        return MenuItemDetailResponse.builder()
                .id(menuItem.getId())
                .menuName(menuItem.getMenuName().getMenuName())
                .price(menuItem.getPrice().getPrice())
                .details(menuItem.getDetails().stream().toList())
                .build();
    }
}
