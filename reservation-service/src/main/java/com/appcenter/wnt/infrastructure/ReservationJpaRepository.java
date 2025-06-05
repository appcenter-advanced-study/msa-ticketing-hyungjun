package com.appcenter.wnt.infrastructure;

import com.appcenter.wnt.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserInfo_UserId(Long userId);
}
