package com.appcenter.wnt.presentation;

import com.appcenter.wnt.application.StoreService;
import com.appcenter.wnt.application.dto.response.StoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal/stores")
@RequiredArgsConstructor
public class StoreInternalController {
    private final StoreService storeService;

    // 가게 조회(내부 조회)
    @GetMapping("/{storeId}")
    public StoreResponse getInternalStore(@PathVariable("storeId") Long storeId) {
        return storeService.findStore(storeId);
    }
}
