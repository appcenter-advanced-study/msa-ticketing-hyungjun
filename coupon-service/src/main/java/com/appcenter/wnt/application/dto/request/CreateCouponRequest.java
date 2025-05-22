package com.appcenter.wnt.application.dto.request;

import com.appcenter.wnt.domain.CouponType;

public record CreateCouponRequest(
        CouponType couponType,
        long quantity
) {
}
