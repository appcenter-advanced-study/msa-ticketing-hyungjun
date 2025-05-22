package com.appcenter.wnt.infrastructure;

import com.appcenter.wnt.domain.Coupon;
import com.appcenter.wnt.domain.CouponRepository;
import com.appcenter.wnt.domain.CouponType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CouponRepositoryImpl implements CouponRepository {
    private final CouponJpaRepository jpaRepository;

    @Override
    public Optional<Coupon> findByType(CouponType couponType) {
        return jpaRepository.findByType(couponType);
    }

    @Override
    public Coupon save(Coupon coupon) {
        return jpaRepository.save(coupon);
    }

    @Override
    public Optional<Coupon> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public void delete(Coupon coupon) {
        jpaRepository.delete(coupon);
    }
}
