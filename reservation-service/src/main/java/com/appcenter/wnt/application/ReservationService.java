package com.appcenter.wnt.application;

import com.appcenter.wnt.application.dto.request.AvailableTimeRequest;
import com.appcenter.wnt.application.dto.request.ReservationRequest;
import com.appcenter.wnt.application.dto.response.AvailableTimeResponse;
import com.appcenter.wnt.application.dto.response.ReservationResponse;
import com.appcenter.wnt.domain.*;
import com.appcenter.wnt.infrastructure.StoreServiceClient;
import com.appcenter.wnt.infrastructure.UserServiceClient;
import com.appcenter.wnt.infrastructure.dto.response.BusinessHourResponse;
import com.appcenter.wnt.infrastructure.dto.response.ReservationInfoResponse;
import com.appcenter.wnt.infrastructure.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final StoreServiceClient storeClient;
    private final UserServiceClient userClient;

    @Transactional
    public ReservationResponse reserve(ReservationRequest request) {
        // 요청한 사용자 정보
        UserResponse user = userClient.getUserById(request.userId());
        log.info("userId : {}", user.id());

        // 서비스를 예약 할 가게 및 메뉴 정보(가게 운영시간 포함)
        ReservationInfoResponse reservationInfo = storeClient.getReservationInfo(request.storeId(), request.menuId());
        log.info("menuId : {}", reservationInfo.menuId());
        log.info("storeId : {}", reservationInfo.storeId());

        // 사용자가 요청한 예약 시간
        ReservationTime reservationTime = new ReservationTime(request.reservationTime().dayOfWeek(), request.reservationTime().reservationTime());

        // 사용자가 요청한 예약 일자
        ReservationDate reservationDate = new ReservationDate(request.reservationDate().reservationDate());

        // 사용자 정보
        UserInfo userInfo = new UserInfo(user.id(), user.nickname());
        // 가게 정보
        StoreInfo storeInfo = new StoreInfo(reservationInfo.storeId(), reservationInfo.storeName(),reservationInfo.fullAddress(),reservationInfo.latitude(),reservationInfo.longitude());
        // 메뉴 정보
        MenuInfo menuInfo = new MenuInfo(reservationInfo.menuId(),reservationInfo.price(), reservationInfo.menuName());

        Reservation reservation = reservationRepository.save(Reservation.of(userInfo,menuInfo,storeInfo, reservationTime,reservationDate));

        return ReservationResponse.from(reservation);
    }

    public AvailableTimeResponse getAvailableTimes(AvailableTimeRequest request) {
        BusinessHourResponse businessHourResponse = storeClient.getBusinessTime(request.storeId());
        List<Reservation> reservations = reservationRepository.findByStoreIdAndReservationDate(request.storeId(), request.reservationDate());

        List<LocalTime> businessTimes = businessHourResponse.businessTimes();
        log.info("businessTimes : {}", businessTimes);
        List<LocalTime> availableTimes = new ArrayList<>(businessTimes);

        if(reservations.isEmpty()) {
            return AvailableTimeResponse.of(businessTimes,availableTimes);
        }


        List<LocalTime> reservationTimes = reservations.stream().map(r -> r.getReservationTime().getReservationTime()).toList();

        for(LocalTime reservationTime : reservationTimes) {
            availableTimes.remove(reservationTime);
        }

        return AvailableTimeResponse.of(businessTimes, availableTimes);
    }

}
