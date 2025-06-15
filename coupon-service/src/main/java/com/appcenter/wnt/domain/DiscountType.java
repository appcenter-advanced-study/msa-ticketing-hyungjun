package com.appcenter.wnt.domain;

import lombok.Getter;

@Getter
public enum DiscountType {
    PERCENTAGE("퍼센트 할인"),
    FIXE("고정 금액 할인");

    DiscountType(String description) {
    }
}
