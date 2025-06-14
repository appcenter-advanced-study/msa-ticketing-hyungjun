package com.appcenter.wnt.consumer;

import com.appcenter.wnt.domain.Coupon;
import com.appcenter.wnt.domain.CouponRepository;
import com.appcenter.wnt.domain.user.UserCoupon;
import com.appcenter.wnt.domain.user.UserCouponRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CouponCreatedConsumer {
    private static final Long SIGN_UP_COUPON_ID = 1L;
    private final CouponRepository couponRepository;
    private final UserCouponRepository userCouponRepository;

    public CouponCreatedConsumer(CouponRepository couponRepository, UserCouponRepository userCouponRepository) {
        this.couponRepository = couponRepository;
        this.userCouponRepository = userCouponRepository;
    }


    @KafkaListener(topics = "coupon-create", groupId = "my-consumer-group")
    public void listener(Long userId){
        log.info("회원 가입시 쿠폰 발급 consumer userId : {}", userId);
        Coupon coupon = couponRepository.findById(SIGN_UP_COUPON_ID).orElseThrow(()->new RuntimeException("coupon could not be found"));
        coupon.issue();
        UserCoupon newUserCoupon = userCouponRepository.issuedCoupon(UserCoupon.of(SIGN_UP_COUPON_ID,userId, coupon.getCouponDateInfo().getExpiredAt()));
        userCouponRepository.issuedCoupon(newUserCoupon);
    }
}
