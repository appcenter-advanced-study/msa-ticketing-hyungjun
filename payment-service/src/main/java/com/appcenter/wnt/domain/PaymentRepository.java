package com.appcenter.wnt.domain;

import java.util.Optional;

public interface PaymentRepository {
    Payment save(Payment payment);
    Optional<Payment> findByReservationId(Long reservationId);
}
