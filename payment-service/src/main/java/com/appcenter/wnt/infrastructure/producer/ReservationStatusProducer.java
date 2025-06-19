package com.appcenter.wnt.infrastructure.producer;


import com.appcenter.wnt.infrastructure.event.ReservationStatusEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReservationStatusProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;


    public void cancelReservation(Long reservationId, boolean isApprove) {
        log.info("예매 취소 reservationid: {}", reservationId);
        try{
            String json = objectMapper.writeValueAsString(new ReservationStatusEvent(reservationId, isApprove));
            kafkaTemplate.send("reservation-status",json);
            log.info("예매 취소 이벤트 발송 완료 : {}", json);
        }catch (Exception e){
            log.info("예매 취소 이벤트 발송 실패");
        }
    }

    public void approveReservation(Long reservationId, boolean isApprove) {
        log.info("예매 성공 reservationid: {}", reservationId);
        try{
            String json = objectMapper.writeValueAsString(new ReservationStatusEvent(reservationId, isApprove));
            kafkaTemplate.send("reservation-status",json);
            log.info("예매 성공 이벤트 발송 완료 : {}", json);
        }catch (Exception e){
            log.info("예매 성공 이벤트 발송 실패");
        }
    }


}