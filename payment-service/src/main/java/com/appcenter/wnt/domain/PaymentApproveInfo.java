package com.appcenter.wnt.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentApproveInfo {
    private String tid;
    private String paymentMethodType;
    private LocalDateTime approvedAt;
    private int totalAmount;
}
