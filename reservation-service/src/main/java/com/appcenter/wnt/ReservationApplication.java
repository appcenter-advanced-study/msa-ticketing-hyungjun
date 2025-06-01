package com.appcenter.wnt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ReservationApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ReservationApplication.class);
        app.setAdditionalProfiles("reservation");
        app.run(args);
    }
}
