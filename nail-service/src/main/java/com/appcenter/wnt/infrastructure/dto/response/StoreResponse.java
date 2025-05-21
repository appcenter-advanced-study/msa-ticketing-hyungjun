package com.appcenter.wnt.infrastructure.dto.response;

public record StoreResponse(
        Long id,
        Long userId,
        String storeName
) {}
