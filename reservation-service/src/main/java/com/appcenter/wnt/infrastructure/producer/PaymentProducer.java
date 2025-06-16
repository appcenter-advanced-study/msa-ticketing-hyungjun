package com.appcenter.wnt.infrastructure.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void payment(PaymentEvent event){
        log.info("예약 시 결제 이벤트 발행 userId: {}, menuName:{}, price:{}", event.userId(),event.menuName(),event.price());
        try{
            String json = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("payment-request", json);
            log.info("결제 이벤트 발송 완료: {}", json);
        }catch (Exception e){
            log.info("결제 이벤트 직렬화 실패: {}",event,e);
        }
    }
}
