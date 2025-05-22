package com.appcenter.wnt.infrastructure;

import com.appcenter.wnt.infrastructure.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "user-service",
        url  = "${user.service.url:http://localhost:8081}"
)
public interface UserServiceClient {
    @GetMapping("/api/internal/users/{id}")
    UserResponse getUserById(@PathVariable("id") Long id);
}
