package com.appcenter.wnt.presentation;

import com.appcenter.wnt.application.StoreImageService;
import com.appcenter.wnt.application.dto.request.CreateStoreRequest;
import com.appcenter.wnt.application.dto.response.StoreDetailPageResponse;
import com.appcenter.wnt.application.dto.response.StoreResponse;
import com.appcenter.wnt.application.StoreService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Store",description = "Store API")
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final StoreImageService storeImageService;

    // 가게 생성
    @PostMapping
    public ResponseEntity<StoreResponse> createStore(@RequestBody CreateStoreRequest request) {
        StoreResponse response = storeService.createStore(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(value = "/{storeId}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String,String>> saveOnlyImage(@RequestPart List<MultipartFile> images, @PathVariable("storeId") Long storeId){
        storeImageService.storeImages(storeId, images);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("결과", "성공!"));
    }
    // 특정 가게 메인 페이지 조회
    @GetMapping("/{storeId}")
    public ResponseEntity<StoreDetailPageResponse> getStoreDetails(@PathVariable("storeId") Long storeId) {
        StoreDetailPageResponse response = storeService.getStoreDetails(storeId);
        return ResponseEntity.ok(response);
    }

    // 가게 전체 목록 조회
    @GetMapping
    public ResponseEntity<List<StoreResponse>> getAllStores() {
        List<StoreResponse> response = storeService.findAllStore();
        return ResponseEntity.ok(response);
    }

    // 가게 삭제
    @DeleteMapping("/{storeId}")
    public ResponseEntity<Void> deleteStore(@PathVariable("storeId") Long storeId) {
        storeService.deleteStore(storeId);
        return ResponseEntity.noContent().build();
    }
}
