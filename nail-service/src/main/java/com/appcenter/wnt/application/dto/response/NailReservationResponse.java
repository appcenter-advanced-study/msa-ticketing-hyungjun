package com.appcenter.wnt.application.dto.response;

import com.appcenter.wnt.domain.NailReservation;
import lombok.Builder;

@Builder
public record NailReservationResponse(
        Long nailReservationId,
        Long storeId,
        Long userId,
        String nailType,
        String description,
        long price
) {
    public static NailReservationResponse from(NailReservation nailReservation) {
        return NailReservationResponse.builder()
                .nailReservationId(nailReservation.getId())
                .userId(nailReservation.getUserId())
                .storeId(nailReservation.getStoreId())
                .nailType(nailReservation.getNailCategory().name())
                .description(nailReservation.getNailCategory().getDescription())
                .price(nailReservation.getNailCategory().getPrice())
                .build();
    }
}
