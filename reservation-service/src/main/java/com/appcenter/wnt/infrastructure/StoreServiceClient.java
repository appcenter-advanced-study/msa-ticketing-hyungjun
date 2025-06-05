package com.appcenter.wnt.infrastructure;

import com.appcenter.wnt.infrastructure.dto.response.ReservationInfoResponse;
import com.appcenter.wnt.infrastructure.dto.response.StoreResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "store-service",
        url  = "${store.service.url:http://localhost:8082}"
)
public interface StoreServiceClient {
    @GetMapping("/api/internal/stores/{id}")
    StoreResponse getStoreById(@PathVariable("id") Long id);

    @GetMapping("/api/internal/stores/reservation")
    ReservationInfoResponse getReservationInfo(@RequestParam("storeId") Long storeId, @RequestParam("menuItemId") Long menuItemId);

}
