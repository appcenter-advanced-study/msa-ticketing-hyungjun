package com.appcenter.wnt.application;

import com.appcenter.wnt.application.dto.request.CreateCouponRequest;
import com.appcenter.wnt.application.dto.response.CouponResponse;
import com.appcenter.wnt.domain.Coupon;
import com.appcenter.wnt.domain.CouponDateInfo;
import com.appcenter.wnt.domain.CouponDiscountInfo;
import com.appcenter.wnt.domain.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CouponService {
    private final CouponRepository couponRepository;

    @Transactional
    public CouponResponse createCoupon(CreateCouponRequest request) {
        CouponDiscountInfo discountInfo = new CouponDiscountInfo(request.couponType(),request.discountValue());
        CouponDateInfo couponDateInfo = new CouponDateInfo(request.couponDateInfoDto().issuedAt(), request.couponDateInfoDto().expiredAt());
        Coupon newCoupon = Coupon.of(couponDateInfo, discountInfo, request.name(), request.quantity());
        couponRepository.save(newCoupon);
        return CouponResponse.from(newCoupon);
    }


    public CouponResponse getCoupon(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId).orElseThrow(()-> new RuntimeException("쿠폰이 존재하지 않습니다."));
        return CouponResponse.from(coupon);
    }
}
