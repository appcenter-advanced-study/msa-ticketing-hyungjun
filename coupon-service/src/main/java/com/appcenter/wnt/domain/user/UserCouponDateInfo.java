package com.appcenter.wnt.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCouponDateInfo {

    @Column(name = "expired_at")
    private LocalDate expiredAt;

    public boolean isValidNow(){
        LocalDate today = LocalDate.now();
        return expiredAt.isBefore(today);
    }
}
