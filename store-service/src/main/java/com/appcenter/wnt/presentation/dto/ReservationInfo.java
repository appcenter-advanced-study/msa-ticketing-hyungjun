package com.appcenter.wnt.presentation.dto;

import com.appcenter.wnt.domain.menu.MenuItem;
import com.appcenter.wnt.domain.store.Store;
import lombok.Builder;

@Builder
public record ReservationInfo(
        Long storeId,
        String storeName,
        String fullAddress,
        Double latitude,
        Double longitude,
        Long menuId,
        String menuName,
        int price
) {
    public static ReservationInfo of(Store store, MenuItem menuItem) {
        return ReservationInfo.builder()
                .storeId(store.getId())
                .storeName(store.getStoreName().getStoreName())
                .fullAddress(store.getAddress().getFullAddress())
                .latitude(store.getAddress().getLatitude())
                .longitude(store.getAddress().getLongitude())
                .menuId(menuItem.getId())
                .menuName(menuItem.getMenuName().getMenuName())
                .price(menuItem.getPrice().getPrice())
                .build();
    }
}
