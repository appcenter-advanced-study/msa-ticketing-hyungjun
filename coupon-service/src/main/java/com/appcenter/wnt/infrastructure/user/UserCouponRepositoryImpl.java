package com.appcenter.wnt.infrastructure.user;

import com.appcenter.wnt.domain.user.UserCoupon;
import com.appcenter.wnt.domain.user.UserCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserCouponRepositoryImpl implements UserCouponRepository {

    private final UserCouponJpaRepository jpaRepository;

    @Override
    public boolean hasSameCoupon(Long userId, Long couponId) {
        return jpaRepository.existsByUserIdAndCouponId(userId, couponId);
    }

    @Override
    public UserCoupon issuedCoupon(UserCoupon userCoupon) {
        jpaRepository.save(userCoupon);
        return userCoupon;
    }
}
