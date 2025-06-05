package com.appcenter.wnt.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class StoreInfo {

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "full_address")
    private String fullAddress;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    public StoreInfo(Long storeId, String storeName, String fullAddress, Double latitude, Double longitude) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.fullAddress = fullAddress;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
