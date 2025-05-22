package com.appcenter.wnt.domain;

import java.util.Optional;

public interface CouponStockRepository {
    Optional<CouponStock> findByCouponId(Long couponId);
    // 비관적 락
    Optional<CouponStock> findByIdWithPessimisticLock(Long couponId);
    // 낙관적락
    Optional<CouponStock> findByIdWithOptimisticLock(Long couponId);

    CouponStock save(CouponStock couponStock);
    void delete(CouponStock couponStock);
}
