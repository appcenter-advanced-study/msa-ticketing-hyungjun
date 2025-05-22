package com.appcenter.wnt.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@Embeddable
public class CouponOwner {
    @Column(name = "user_id")
    private Long userId;

    public CouponOwner(Long userId) {
        this.userId = userId;
    }
}
