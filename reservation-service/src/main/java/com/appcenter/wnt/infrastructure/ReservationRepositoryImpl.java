package com.appcenter.wnt.infrastructure;

import com.appcenter.wnt.domain.Reservation;
import com.appcenter.wnt.domain.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {
    private final ReservationJpaRepository jpaRepository;

    @Override
    public Reservation save(Reservation reservation) {
        return jpaRepository.save(reservation);
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public void delete(Reservation reservation) {
        jpaRepository.delete(reservation);
    }

    @Override
    public List<Reservation> findByUserId(Long userId) {
        return jpaRepository.findByUserInfo_UserId(userId);
    }
}
