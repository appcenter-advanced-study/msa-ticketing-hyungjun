package com.appcenter.wnt.presentation;

import com.appcenter.wnt.application.UserCouponService;
import com.appcenter.wnt.application.dto.request.CreateCouponRequest;
import com.appcenter.wnt.application.dto.request.IssuedUserCouponRequest;
import com.appcenter.wnt.application.dto.response.CouponResponse;
import com.appcenter.wnt.application.CouponService;
import com.appcenter.wnt.application.dto.response.UserCouponResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Coupon",description = "Coupon API")
@RequestMapping("/api/coupons")
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;
    private final UserCouponService userCouponService;

    // 쿠폰 생성
    @PostMapping
    public ResponseEntity<CouponResponse> createCoupon(@RequestBody CreateCouponRequest request) {
        CouponResponse response = couponService.createCoupon(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 쿠폰 조회
    @GetMapping("/{couponId}")
    public ResponseEntity<CouponResponse> getCoupon(@PathVariable("couponId") Long couponId) {
        CouponResponse response = couponService.getCoupon(couponId);
        return ResponseEntity.ok(response);
    }

    // 유저 쿠폰 발급
    @PostMapping("/users")
    public ResponseEntity<UserCouponResponse> IssuedUserCoupon(@RequestBody IssuedUserCouponRequest request) {
        UserCouponResponse userCouponResponse = userCouponService.issueCoupon(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCouponResponse);
    }

}
