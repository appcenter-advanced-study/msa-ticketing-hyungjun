package com.appcenter.wnt.infrastructure;

import com.appcenter.wnt.domain.CouponStock;
import com.appcenter.wnt.domain.CouponStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CouponStockRepositoryImpl implements CouponStockRepository {
    private final CouponStockJpaRepository jpaRepository;

    @Override
    public Optional<CouponStock> findByCouponId(Long couponId) {
        return jpaRepository.findByCouponId(couponId);
    }

    @Override
    public Optional<CouponStock> findByIdWithPessimisticLock(Long couponId) {
        return jpaRepository.findByIdWithPessimisticLock(couponId);
    }

    @Override
    public Optional<CouponStock> findByIdWithOptimisticLock(Long couponId) {
        return jpaRepository.findByIdWithOptimisticLock(couponId);
    }

    @Override
    public CouponStock save(CouponStock couponStock) {
        return jpaRepository.save(couponStock);
    }

    @Override
    public void delete(CouponStock couponStock) {
        jpaRepository.delete(couponStock);
    }
}
