package com.appcenter.wnt.dto.request;

import com.appcenter.wnt.domain.CouponType;

public record CreateCouponRequest(
        CouponType couponType,
        long quantity
) {
}
