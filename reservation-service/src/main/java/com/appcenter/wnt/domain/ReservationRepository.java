package com.appcenter.wnt.domain;

import com.appcenter.wnt.domain.enums.NailReservationTime;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    Reservation save(Reservation nailReservation);
    Optional<Reservation> findById(Long id);
    void delete(Reservation nailReservation);
    Optional<Reservation> findByStoreIdAndReservationDateAndReservationTime(Long storeId, LocalDate date, NailReservationTime time);
    List<Reservation> findByUserId(Long userId);
}
