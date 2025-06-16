package com.appcenter.wnt.infrastructure;

public record PaymentEvent(
        Long reservationId,
        Long userId,
        String storeName,
        String menuName,
        int price
) {
}
