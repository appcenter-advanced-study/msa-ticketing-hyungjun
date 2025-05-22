package com.appcenter.wnt.domain;

import com.appcenter.wnt.domain.enums.NailReservationTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface NailReservationRepository{
    NailReservation save(NailReservation nailReservation);
    Optional<NailReservation> findById(Long id);
    void delete(NailReservation nailReservation);
    Optional<NailReservation> findByStoreIdAndReservationDateAndReservationTime(Long storeId, LocalDate date, NailReservationTime time);
    List<NailReservation> findByUserId(Long userId);
}
