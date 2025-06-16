package com.appcenter.wnt.infrastructure.consumer;

import com.appcenter.wnt.domain.Payment;
import com.appcenter.wnt.domain.PaymentRepository;
import com.appcenter.wnt.infrastructure.PaymentEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentConsumer {

    private final ObjectMapper objectMapper;
    private final PaymentRepository paymentRepository;

    @KafkaListener(topics = "payment-request", groupId = "my-consumer-group")
    public void listener(String message){
        try{
            PaymentEvent event = objectMapper.readValue(message, PaymentEvent.class);
            paymentRepository.save(new Payment(event.reservationId(),event.userId(),event.menuName(),event.storeName(),event.price()));
            log.info("결제 내역 저장 완료: {}", event);
        }catch (Exception e){
            log.info("결제 이벤트 역직렬화 실패 : {}", message,e);
        }
    }
}
