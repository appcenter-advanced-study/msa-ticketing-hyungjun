package com.appcenter.wnt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PaymentApplication.class);
        app.setAdditionalProfiles("payment");
        app.run(args);
    }
}
