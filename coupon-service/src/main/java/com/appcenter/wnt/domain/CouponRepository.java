package com.appcenter.wnt.domain;

import java.util.Optional;

public interface CouponRepository {
    Coupon save(Coupon coupon);
    Optional<Coupon> findById(Long id);
    void delete(Coupon coupon);
}
