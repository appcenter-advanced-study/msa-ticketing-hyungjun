package com.appcenter.wnt.dto.response;

import com.appcenter.wnt.domain.Store;
import lombok.Builder;

@Builder
public record StoreResponse(
        Long id,
        Long userId,
        String storeName
) {
    public static StoreResponse from(Store store) {
        return StoreResponse.builder()
                .id(store.getId())
                .userId(store.getUserId())
                .storeName(store.getName())
                .build();
    }
}
