package com.appcenter.wnt.application.dto.response;

import com.appcenter.wnt.domain.user.UserCoupon;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserCouponResponse(
        Long userCouponId,
        Long couponId,
        Long userId,
        LocalDate expiredAt
) {
    public static UserCouponResponse from(UserCoupon userCoupon) {
        return UserCouponResponse.builder()
                .userCouponId(userCoupon.getId())
                .couponId(userCoupon.getCouponId())
                .userId(userCoupon.getUserId())
                .expiredAt(userCoupon.getUserCouponDateInfo().getExpiredAt())
                .build();
    }
}
