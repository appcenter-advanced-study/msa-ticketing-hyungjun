package com.appcenter.wnt.application.dto.request;

public record IssuedUserCouponRequest(
        Long userId,
        Long couponId
) {
}
