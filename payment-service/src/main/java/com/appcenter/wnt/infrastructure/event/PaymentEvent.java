package com.appcenter.wnt.infrastructure.event;

public record PaymentEvent(
        Long reservationId,
        Long userId,
        String storeName,
        String menuName,
        int price
) {
}
