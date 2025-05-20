package com.appcenter.wnt.repository;

import com.appcenter.wnt.domain.NailReservation;
import com.appcenter.wnt.domain.enums.NailReservationTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface NailReservationRepository extends JpaRepository<NailReservation, Long> {
    Optional<NailReservation> findByStoreIdAndReservationDateAndReservationTime(Long storeId, LocalDate date, NailReservationTime time);
    List<NailReservation> findByUserId(Long userId);
}
