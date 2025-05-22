package com.appcenter.wnt.infrastructure;

import com.appcenter.wnt.domain.Coupon;
import com.appcenter.wnt.domain.CouponType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponJpaRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByType(CouponType couponType);
}
