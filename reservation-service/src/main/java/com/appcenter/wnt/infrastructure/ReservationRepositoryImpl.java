package com.appcenter.wnt.infrastructure;

import com.appcenter.wnt.domain.Reservation;
import com.appcenter.wnt.domain.ReservationRepository;
import com.appcenter.wnt.domain.enums.NailReservationTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {
    private final ReservationJpaRepository jpaRepository;

    @Override
    public Reservation save(Reservation nailReservation) {
        return jpaRepository.save(nailReservation);
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public void delete(Reservation nailReservation) {
        jpaRepository.delete(nailReservation);
    }

    @Override
    public Optional<Reservation> findByStoreIdAndReservationDateAndReservationTime(Long storeId, LocalDate date, NailReservationTime time) {
        return jpaRepository.findByStoreIdAndReservationDateAndReservationTime(storeId, date, time);
    }

    @Override
    public List<Reservation> findByUserId(Long userId) {
        return jpaRepository.findByUserId(userId);
    }
}
