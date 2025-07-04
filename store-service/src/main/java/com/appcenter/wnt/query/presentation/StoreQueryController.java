package com.appcenter.wnt.query.presentation;

import com.appcenter.wnt.application.dto.response.StoreDetailPageResponse;
import com.appcenter.wnt.application.dto.response.StoreResponse;
import com.appcenter.wnt.query.application.StoreQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Store",description = "Store API")
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreQueryController {
    private final StoreQueryService storeQueryService;

    // 특정 가게 메인 페이지 조회
    @GetMapping("/{storeId}")
    public ResponseEntity<StoreDetailPageResponse> getStoreDetails(@PathVariable("storeId") Long storeId) {
        StoreDetailPageResponse response = storeQueryService.getStoreDetails(storeId);
        return ResponseEntity.ok(response);
    }

    // 가게 전체 목록 조회
    @GetMapping
    public ResponseEntity<List<StoreResponse>> getAllStores() {
        List<StoreResponse> response = storeQueryService.findAllStore();
        return ResponseEntity.ok(response);
    }
}
