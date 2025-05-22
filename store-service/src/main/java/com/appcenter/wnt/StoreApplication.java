package com.appcenter.wnt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StoreApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(StoreApplication.class);
        app.setAdditionalProfiles("stores");
        app.run(args);    }
}
