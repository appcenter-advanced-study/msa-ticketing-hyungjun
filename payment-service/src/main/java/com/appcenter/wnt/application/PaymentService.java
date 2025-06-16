package com.appcenter.wnt.application;

import com.appcenter.wnt.application.dto.PaymentResponse;
import com.appcenter.wnt.domain.Payment;
import com.appcenter.wnt.domain.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentResponse getPayment(Long reservationId) {
        Payment payment = paymentRepository.findByReservationId(reservationId).orElseThrow(()-> new RuntimeException("결제할 내용이 존재하지 않습니다."));
        return PaymentResponse.from(payment);
    }
}
