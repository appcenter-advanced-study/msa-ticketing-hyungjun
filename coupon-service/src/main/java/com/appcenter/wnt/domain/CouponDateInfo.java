package com.appcenter.wnt.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@Getter
@NoArgsConstructor
@Builder
public class CouponDateInfo {
    @Column(name = "issued_at")
    private LocalDate issuedAt;

    @Column(name = "expired_at")
    private LocalDate expiredAt;

    public CouponDateInfo(LocalDate issuedAt, LocalDate expiredAt) {
        this.issuedAt = issuedAt;
        this.expiredAt = expiredAt;
    }
}
