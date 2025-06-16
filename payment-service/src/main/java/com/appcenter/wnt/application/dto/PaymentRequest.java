package com.appcenter.wnt.application.dto;

public record PaymentRequest(
        Long reservationId,
        Long userId,
        String storeName,
        String menuName,
        int price
) {
}
