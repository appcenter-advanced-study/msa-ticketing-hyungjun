package com.appcenter.wnt.infrastructure;

import com.appcenter.wnt.domain.Reservation;
import com.appcenter.wnt.domain.enums.NailReservationTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByStoreIdAndReservationDateAndReservationTime(Long storeId, LocalDate date, NailReservationTime time);
    List<Reservation> findByUserId(Long userId);
}
