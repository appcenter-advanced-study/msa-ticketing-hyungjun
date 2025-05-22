package com.appcenter.wnt.infrastructure;

import com.appcenter.wnt.domain.CouponReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CouponReservationJpaRepository extends JpaRepository<CouponReservation, Long> {

    Optional<CouponReservation> findByCouponOwnerUserIdAndCouponId(Long userId, Long couponId);
    List<CouponReservation> findByCouponOwnerUserId(Long userId);
}
