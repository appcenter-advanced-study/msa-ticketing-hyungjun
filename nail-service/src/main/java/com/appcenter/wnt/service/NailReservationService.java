package com.appcenter.wnt.service;

import com.appcenter.wnt.client.StoreClient;
import com.appcenter.wnt.client.StoreResponse;
import com.appcenter.wnt.client.UserClient;
import com.appcenter.wnt.client.UserResponse;
import com.appcenter.wnt.domain.NailReservation;
import com.appcenter.wnt.dto.request.NailReservationRequest;
import com.appcenter.wnt.dto.response.NailReservationResponse;
import com.appcenter.wnt.repository.NailReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class NailReservationService {

    private final NailReservationRepository reservationRepository;
    private final StoreClient storeClient;
    private final UserClient userClient;

    @Transactional
    public NailReservationResponse reserve(NailReservationRequest request) {
        UserResponse user = userClient.getUserById(request.userId());
        StoreResponse store = storeClient.getStoreById(request.storeId());

        reservationRepository.findByStoreIdAndReservationDateAndReservationTime(store.id(), request.reservationDate(), request.reservationTime()).ifPresent(nr ->{
            throw new RuntimeException("이미 예약이 존재합니다.");
        });

        NailReservation nailReservation = reservationRepository.save(NailReservation.of(user.id(),user.nickname(),store.id(),store.storeName(),request.nailCategory(),request.reservationDate(),request.reservationTime()));
        return NailReservationResponse.from(nailReservation);
    }

    @Transactional
    public void cancel(Long nailReservationId) {
        NailReservation nailReservation = reservationRepository.findById(nailReservationId).orElseThrow(()-> new RuntimeException("예약이 존재하지 않습니다."));
        reservationRepository.delete(nailReservation);
    }

    @Transactional(readOnly = true)
    public List<NailReservationResponse> getReservations(Long userId) {
        UserResponse user = userClient.getUserById(userId);
        List<NailReservation> nailReservations = reservationRepository.findByUserId(user.id());

        return nailReservations.stream().map(NailReservationResponse::from).collect(Collectors.toList());
    }
}
