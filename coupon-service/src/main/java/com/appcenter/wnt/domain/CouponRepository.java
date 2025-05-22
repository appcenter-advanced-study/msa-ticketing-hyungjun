package com.appcenter.wnt.domain;

import java.util.Optional;

public interface CouponRepository {
    Optional<Coupon> findByType(CouponType couponType);
    Coupon save(Coupon coupon);
    Optional<Coupon> findById(Long id);
    void delete(Coupon coupon);
}
