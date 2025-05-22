package com.appcenter.wnt.domain;

import java.util.List;
import java.util.Optional;

public interface CouponReservationRepository {
    Optional<CouponReservation> findByUserIdAndCouponId(Long userId, Long couponId);
    List<CouponReservation> findByUserId(Long userId);
    CouponReservation save(CouponReservation couponReservation);
    void delete(CouponReservation couponReservation);
}
