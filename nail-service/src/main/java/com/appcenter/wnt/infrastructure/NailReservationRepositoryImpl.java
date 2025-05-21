package com.appcenter.wnt.infrastructure;

import com.appcenter.wnt.domain.NailReservation;
import com.appcenter.wnt.domain.NailReservationRepository;
import com.appcenter.wnt.domain.enums.NailReservationTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NailReservationRepositoryImpl implements NailReservationRepository {
    private final NailReservationJpaRepository jpaRepository;

    @Override
    public NailReservation save(NailReservation nailReservation) {
        return jpaRepository.save(nailReservation);
    }

    @Override
    public Optional<NailReservation> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public void delete(NailReservation nailReservation) {
        jpaRepository.delete(nailReservation);
    }

    @Override
    public Optional<NailReservation> findByStoreIdAndReservationDateAndReservationTime(Long storeId, LocalDate date, NailReservationTime time) {
        return jpaRepository.findByStoreIdAndReservationDateAndReservationTime(storeId, date, time);
    }

    @Override
    public List<NailReservation> findByUserId(Long userId) {
        return jpaRepository.findByUserId(userId);
    }
}
