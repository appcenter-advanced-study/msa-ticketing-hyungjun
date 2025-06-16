package com.appcenter.wnt.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long reservationId;
    private Long userId;
    private String menuName;
    private String storeName;
    private int price;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private LocalDateTime  expiresAt;

    @Embedded
    private PaymentApproveInfo paymentApproveInfo;

    public Payment(Long reservationId,Long userId, String menuName, String storeName, int price) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.menuName = menuName;
        this.storeName = storeName;
        this.price = price;
        this.status = PaymentStatus.PENDING;
        this.expiresAt = LocalDateTime.now().plusMinutes(5);
    }

    public void cancel() {
        this.status = PaymentStatus.CANCEL;
    }

    public void approve(PaymentApproveInfo approveInfo) {
        this.status = PaymentStatus.APPROVE;
        this.paymentApproveInfo = approveInfo;
    }

    public void fail() {
        this.status = PaymentStatus.FAIL;
    }

}
