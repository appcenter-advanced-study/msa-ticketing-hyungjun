package com.appcenter.wnt.infrastructure.consumer;

import com.appcenter.wnt.domain.Reservation;
import com.appcenter.wnt.domain.ReservationRepository;
import com.appcenter.wnt.infrastructure.event.ReservationStatusEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ReservationStatusConsumer {

    private final ObjectMapper objectMapper;
    private final ReservationRepository reservationRepository;

    @KafkaListener(topics = "reservation-status", groupId = "my-consumer-group")
    public void listener(String message){
        try{
            ReservationStatusEvent event = objectMapper.readValue(message, ReservationStatusEvent.class);
            Reservation reservation = reservationRepository.findById(event.reservationId()).orElseThrow(()-> new RuntimeException("예매가 존재하지 않습니다."));
            if(event.isApprove()){
                reservation.approve();
                log.info("결제 성공 및 예매 승인: {}", event);
            }else{
                reservation.cancel();
                log.info("결제 실패 및 예매 취소");
            }
        }catch (Exception e){
            log.info("결제 이벤트 역직렬화 실패 : {}", message,e);
        }
    }
}
