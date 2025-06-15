package com.appcenter.wnt.application.dto.response;

import com.appcenter.wnt.domain.store.*;
import lombok.Builder;

import java.time.LocalTime;

@Builder
public record StoreDetailPageResponse(
        String name,
        String address,
        String phone,
        String instagram,
        String storeStatus,
        LocalTime endTime
) {
    public static StoreDetailPageResponse from(Store store, LocalTime endTime){
        StoreName storeName = store.getStoreName();
        Address address = store.getAddress();
        ContactInfo contactInfo = store.getContactInfo();

        return StoreDetailPageResponse.builder()
                .name(storeName.getStoreName())
                .address(address.getFullAddress())
                .phone(contactInfo.getPhone())
                .instagram(contactInfo.getInstagram())
                .storeStatus(store.getStatus().getDescription())
                .endTime(endTime)
                .build();
    }
}
