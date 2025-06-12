package com.appcenter.wnt.infrastructure;

import com.appcenter.wnt.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserInfo_UserId(Long userId);
    List<Reservation> findByStoreInfo_StoreIdAndReservationDate_ReservationDate(Long storeId, LocalDate reservationDate);
}
