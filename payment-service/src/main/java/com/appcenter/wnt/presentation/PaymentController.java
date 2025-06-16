package com.appcenter.wnt.presentation;

import com.appcenter.wnt.application.PaymentService;
import com.appcenter.wnt.application.dto.PaymentResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Payment",description = "Payment API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/{reservationId}")
    public ResponseEntity<PaymentResponse> getPayment(@PathVariable("reservationId") Long reservationId) {
        PaymentResponse payment = paymentService.getPayment(reservationId);
        return ResponseEntity.ok(payment);
    }
}
