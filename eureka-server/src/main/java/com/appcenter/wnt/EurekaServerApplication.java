package com.appcenter.wnt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(EurekaServerApplication.class);
        app.setAdditionalProfiles("eureka");
        app.run(args);
    }
}
