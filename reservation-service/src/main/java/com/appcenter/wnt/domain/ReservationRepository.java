package com.appcenter.wnt.domain;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    Reservation save(Reservation reservation);
    Optional<Reservation> findById(Long id);
    void delete(Reservation Reservation);
    List<Reservation> findByUserId(Long userId);
}
