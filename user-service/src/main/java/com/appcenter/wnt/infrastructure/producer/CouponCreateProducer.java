package com.appcenter.wnt.infrastructure.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CouponCreateProducer {

    private final KafkaTemplate<String, Long> kafkaTemplate;
    public CouponCreateProducer(final KafkaTemplate<String, Long> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void create(Long userId){
        log.info("회원가입 시 쿠폰 발급 kafka userId: {}", userId);
        kafkaTemplate.send("coupon-create", userId);
    }
}
