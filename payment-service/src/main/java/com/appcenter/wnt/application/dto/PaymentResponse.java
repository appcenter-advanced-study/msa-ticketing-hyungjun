package com.appcenter.wnt.application.dto;

import com.appcenter.wnt.domain.Payment;
import lombok.Builder;

@Builder
public record PaymentResponse(
        Long reservationId,
        Long userId,
        String storeName,
        String menuName,
        int price
) {
    public static PaymentResponse from(Payment payment)
    {
        return PaymentResponse.builder()
                .reservationId(payment.getReservationId())
                .userId(payment.getUserId())
                .storeName(payment.getStoreName())
                .menuName(payment.getMenuName())
                .price(payment.getPrice())
                .build();
    }
}
