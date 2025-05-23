package com.appcenter.wnt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UserApplication.class);
        app.setAdditionalProfiles("users");
        app.run(args);
    }
}
