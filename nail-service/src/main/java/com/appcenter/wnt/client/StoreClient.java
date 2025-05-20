package com.appcenter.wnt.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "store-service",
        url  = "${store.service.url:http://localhost:8082}"
)
public interface StoreClient {
    @GetMapping("/api/stores/{id}")
    StoreResponse getStoreById(@PathVariable("id") Long id);
}
