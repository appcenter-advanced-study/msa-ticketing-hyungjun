package com.appcenter.wnt.domain.user;

public interface UserCouponRepository {
    boolean hasSameCoupon(Long userId, Long couponId);
    UserCoupon issuedCoupon(UserCoupon userCoupon);
}
