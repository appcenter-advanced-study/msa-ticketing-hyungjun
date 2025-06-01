package com.appcenter.wnt.infrastructure;

import com.appcenter.wnt.infrastructure.dto.response.StoreResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "store-service",
        url  = "${store.service.url:http://localhost:8082}"
)
public interface StoreServiceClient {
    @GetMapping("/api/internal/stores/{id}")
    StoreResponse getStoreById(@PathVariable("id") Long id);
}
