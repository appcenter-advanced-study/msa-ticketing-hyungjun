package com.appcenter.wnt.application.dto.response;

import com.appcenter.wnt.domain.Coupon;
import com.appcenter.wnt.domain.CouponDateInfo;
import com.appcenter.wnt.domain.CouponDiscountInfo;
import com.appcenter.wnt.domain.DiscountType;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CouponResponse(
        Long id,
        String name,
        int quantity,

        // 발급 및 만료일
        LocalDate issuedAt,
        LocalDate expiredAt,

        // 할인 정보
        DiscountType discountType,
        int discountValue
) {
    public static CouponResponse from(Coupon coupon) {
        CouponDateInfo dateInfo = coupon.getCouponDateInfo();
        CouponDiscountInfo discountInfo = coupon.getCouponDiscountInfo();

        return CouponResponse.builder()
                .id(coupon.getId())
                .name(coupon.getName())
                .quantity(coupon.getQuantity())
                .issuedAt(dateInfo.getIssuedAt())
                .expiredAt(dateInfo.getExpiredAt())
                .discountType(discountInfo.getDiscountType())
                .discountValue(discountInfo.getDiscountValue())
                .build();
    }
}
