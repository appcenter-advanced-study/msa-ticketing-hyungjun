package com.appcenter.wnt.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="coupons")
public class Coupon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    @Embedded
    private CouponDateInfo couponDateInfo;

    @Embedded
    private CouponDiscountInfo couponDiscountInfo;

    @Column(name = "coupon_name")
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @Builder
    private Coupon(CouponDateInfo couponDateInfo,CouponDiscountInfo couponDiscountInfo, String name, int quantity) {
        this.couponDateInfo = couponDateInfo;
        this.couponDiscountInfo = couponDiscountInfo;
        this.name = name;
        this.quantity = quantity;
    }

    public static Coupon of(CouponDateInfo couponDateInfo, CouponDiscountInfo couponDiscountInfo, String name, int quantity) {
        return Coupon.builder().couponDateInfo(couponDateInfo).couponDiscountInfo(couponDiscountInfo).name(name).quantity(quantity).build();
    }

    public void issue(){
        if(this.quantity <= 0){
            throw new RuntimeException("쿠폰을 발급할 수 없습니다.");
        }
        this.quantity--;
    }
}
