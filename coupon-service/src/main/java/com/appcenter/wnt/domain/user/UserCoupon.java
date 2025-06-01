package com.appcenter.wnt.domain.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user_coupon")
public class UserCoupon {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coupon_id")
    private Long couponId;

    @Column(name = "user_id")
    private Long userId;

    @Embedded
    private UserCouponDateInfo userCouponDateInfo;

    @Builder
    private UserCoupon(Long couponId, Long userId,LocalDate expiredAt) {
        this.couponId = couponId;
        this.userId = userId;
        this.userCouponDateInfo = new UserCouponDateInfo(expiredAt);
    }

    public static UserCoupon of(Long couponId, Long userId, LocalDate expiredAt) {
        return UserCoupon.builder().userId(userId).couponId(couponId).expiredAt(expiredAt).build();
    }
}
