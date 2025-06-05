package com.appcenter.wnt.infrastructure.dto.response;

public record ReservationInfoResponse(
        Long storeId,
        String storeName,
        String fullAddress,
        Double latitude,
        Double longitude,
        Long menuId,
        String menuName,
        int price
) {
}
