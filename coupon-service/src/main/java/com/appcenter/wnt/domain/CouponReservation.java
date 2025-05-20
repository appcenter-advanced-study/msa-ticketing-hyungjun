package com.appcenter.wnt.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="coupon_reservations")
public class CouponReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_reservation_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "coupon_id")
    private Long couponId;

    @Enumerated(EnumType.STRING)
    @Column(name = "coupon_type")
    private CouponType couponType;

    @Builder
    private CouponReservation(Long userId, Long couponId, CouponType couponType) {
        this.userId = userId;
        this.couponId = couponId;
        this.couponType = couponType;
    }

    public static CouponReservation of(Long userId, Long couponId, CouponType couponType) {
        return CouponReservation.builder().userId(userId).couponId(couponId).couponType(couponType).build();
    }
}
