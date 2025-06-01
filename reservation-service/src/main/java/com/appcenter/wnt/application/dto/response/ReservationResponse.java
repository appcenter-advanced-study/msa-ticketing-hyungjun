package com.appcenter.wnt.application.dto.response;

import com.appcenter.wnt.domain.Reservation;
import lombok.Builder;

@Builder
public record ReservationResponse(
        Long reservationId,
        Long storeId,
        Long userId,
        String nailType,
        String description,
        long price
) {
    public static ReservationResponse from(Reservation reservation) {
        return ReservationResponse.builder()
                .reservationId(reservation.getId())
                .userId(reservation.getUserId())
                .storeId(reservation.getStoreId())
                .nailType(reservation.getNailCategory().name())
                .description(reservation.getNailCategory().getDescription())
                .price(reservation.getNailCategory().getPrice())
                .build();
    }
}
