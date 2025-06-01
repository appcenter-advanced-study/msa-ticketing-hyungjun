package com.appcenter.wnt.application;

import com.appcenter.wnt.application.dto.request.IssuedUserCouponRequest;
import com.appcenter.wnt.application.dto.response.UserCouponResponse;
import com.appcenter.wnt.domain.Coupon;
import com.appcenter.wnt.domain.CouponRepository;
import com.appcenter.wnt.domain.user.UserCoupon;
import com.appcenter.wnt.domain.user.UserCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserCouponService {
    private final CouponRepository couponRepository;
    private final UserCouponRepository userCouponRepository;

    @Transactional
    public UserCouponResponse issueCoupon(IssuedUserCouponRequest request) {
        Long userId = request.userId();
        Long couponId = request.couponId();
        if(userCouponRepository.hasSameCoupon(userId, couponId)){
            throw new RuntimeException("해당 유저는 이미 쿠폰이 존재합니다.");
        }

        Coupon coupon = couponRepository.findById(couponId).orElseThrow(()-> new RuntimeException("쿠폰이 존재하지 않습니다."));
        coupon.issue();
        UserCoupon newUserCoupon = userCouponRepository.issuedCoupon(UserCoupon.of(userId, couponId, coupon.getCouponDateInfo().getExpiredAt()));

        return UserCouponResponse.from(newUserCoupon);
    }
}
