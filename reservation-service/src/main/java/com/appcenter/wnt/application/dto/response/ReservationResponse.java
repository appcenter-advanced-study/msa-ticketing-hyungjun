package com.appcenter.wnt.application.dto.response;

import com.appcenter.wnt.domain.Reservation;
import lombok.Builder;

@Builder
public record ReservationResponse(
        Long reservationId,
        Long storeId,
        Long userId,
        Long menuId
) {
    public static ReservationResponse from(Reservation reservation) {
        return ReservationResponse.builder()
                .reservationId(reservation.getId())
                .storeId(reservation.getStoreInfo().getStoreId())
                .userId(reservation.getUserInfo().getUserId())
                .menuId(reservation.getMenuInfo().getMenuId())
                .build();
    }
}
