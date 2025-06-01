package com.appcenter.wnt.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponDiscountInfo {
    @Enumerated(EnumType.STRING)
    private DiscountType discountType;
    private int discountValue;
}
