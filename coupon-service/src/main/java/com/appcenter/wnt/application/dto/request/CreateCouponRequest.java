package com.appcenter.wnt.application.dto.request;

import com.appcenter.wnt.domain.DiscountType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record CreateCouponRequest(
        DiscountType couponType,
        int discountValue,
        CouponDateInfoDto couponDateInfoDto,
        String name,
        int quantity
) {

    public record CouponDateInfoDto(
            @JsonFormat(pattern = "yyyy-MM-dd")
            LocalDate issuedAt,

            @JsonFormat(pattern = "yyyy-MM-dd")
            LocalDate expiredAt
    ) {
    }
}
