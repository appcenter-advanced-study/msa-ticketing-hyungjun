//package com.appcenter.wnt;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.boot.WebApplicationType;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//
//@SpringBootConfiguration
//public class ModularMonolithsApplication {
//    public static void main(String[] args) {
//        SpringApplication bootstrap = new SpringApplication(ModularMonolithsApplication.class);
//        // 이 한 줄만 추가하면 bootstrap 컨텍스트에서 톰캣을 띄우지 않습니다
//        bootstrap.setWebApplicationType(WebApplicationType.NONE);
//        bootstrap.run(args);
//
//        // 1) User 모듈 독립 실행 (application-gateway.yml 로딩 + 포트 8081 강제 설정)
//        new SpringApplicationBuilder(UserApplication.class)
//                .profiles("users")
//                .run(args);
//
//        // 2) ApiGateway 모듈 독립 실행 (application-gateway.yml 로딩 + 포트 8080 강제 설정)
//        new SpringApplicationBuilder(ApiGatewayApplication.class)
//                .profiles("gateway")
//                .run(args);
//    }
//}
