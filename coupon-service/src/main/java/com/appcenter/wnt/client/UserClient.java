package com.appcenter.wnt.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "user-service",
        url  = "${user.service.url:http://localhost:8081}"
)
public interface UserClient {
    @GetMapping("/api/users/{id}")
    UserResponse getUserById(@PathVariable("id") Long id);
}
