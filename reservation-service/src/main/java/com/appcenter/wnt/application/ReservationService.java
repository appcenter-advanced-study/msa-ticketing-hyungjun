package com.appcenter.wnt.application;

import com.appcenter.wnt.application.dto.request.ReservationRequest;
import com.appcenter.wnt.application.dto.response.ReservationResponse;
import com.appcenter.wnt.domain.Reservation;
import com.appcenter.wnt.domain.ReservationRepository;
import com.appcenter.wnt.infrastructure.StoreServiceClient;
import com.appcenter.wnt.infrastructure.UserServiceClient;
import com.appcenter.wnt.infrastructure.dto.response.StoreResponse;
import com.appcenter.wnt.infrastructure.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final StoreServiceClient storeClient;
    private final UserServiceClient userClient;

    @Transactional
    public ReservationResponse reserve(ReservationRequest request) {
        UserResponse user = userClient.getUserById(request.userId());
        StoreResponse store = storeClient.getStoreById(request.storeId());

        reservationRepository.findByStoreIdAndReservationDateAndReservationTime(store.id(), request.reservationDate(), request.reservationTime()).ifPresent(nr ->{
            throw new RuntimeException("이미 예약이 존재합니다.");
        });

        Reservation nailReservation = reservationRepository.save(Reservation.of(user.id(),user.nickname(),store.id(),store.storeName(),request.nailCategory(),request.reservationDate(),request.reservationTime()));
        return ReservationResponse.from(nailReservation);
    }

    @Transactional
    public void cancel(Long nailReservationId) {
        Reservation nailReservation = reservationRepository.findById(nailReservationId).orElseThrow(()-> new RuntimeException("예약이 존재하지 않습니다."));
        reservationRepository.delete(nailReservation);
    }

    @Transactional(readOnly = true)
    public List<ReservationResponse> getReservations(Long userId) {
        UserResponse user = userClient.getUserById(userId);
        List<Reservation> nailReservations = reservationRepository.findByUserId(user.id());

        return nailReservations.stream().map(ReservationResponse::from).collect(Collectors.toList());
    }
}
