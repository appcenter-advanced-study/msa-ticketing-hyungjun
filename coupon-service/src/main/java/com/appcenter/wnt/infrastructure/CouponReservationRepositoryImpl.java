package com.appcenter.wnt.infrastructure;

import com.appcenter.wnt.domain.CouponReservation;
import com.appcenter.wnt.domain.CouponReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CouponReservationRepositoryImpl implements CouponReservationRepository {
    private final CouponReservationJpaRepository jpaRepository;

    @Override
    public Optional<CouponReservation> findByUserIdAndCouponId(Long userId, Long couponId) {
        return jpaRepository.findByCouponOwnerUserIdAndCouponId(userId, couponId);
    }

    @Override
    public List<CouponReservation> findByUserId(Long userId) {
        return jpaRepository.findByCouponOwnerUserId(userId);
    }

    @Override
    public CouponReservation save(CouponReservation couponReservation) {
        return jpaRepository.save(couponReservation);
    }

    @Override
    public void delete(CouponReservation couponReservation) {
        jpaRepository.delete(couponReservation);
    }
}
