package com.appcenter.wnt.infrastructure;

import com.appcenter.wnt.domain.Coupon;
import com.appcenter.wnt.domain.CouponRepository;
import com.appcenter.wnt.domain.DiscountType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CouponRepositoryImpl implements CouponRepository {
    private final CouponJpaRepository jpaRepository;

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
