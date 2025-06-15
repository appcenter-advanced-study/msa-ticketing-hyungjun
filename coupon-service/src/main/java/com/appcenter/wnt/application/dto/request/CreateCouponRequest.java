package com.appcenter.wnt.application.dto.request;

import com.appcenter.wnt.domain.DiscountType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record CreateCouponRequest(
        @Schema(example = "PERCENTAGE")
        DiscountType couponType,
        @Schema(example = "30")
        int discountValue,
        CouponDateInfoDto couponDateInfoDto,
        @Schema(example = "신규 유저 할인 쿠폰")
        String name,
        @Schema(example = "1000")
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
