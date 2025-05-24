package com.appcenter.wnt.domain.store;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@Builder
public class Address {

    @Column(name = "full_address")
    private String fullAddress;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    public Address(String fullAddress, Double latitude, Double longitude) {
        this.fullAddress = fullAddress;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Address of(String fullAddress, Double latitude, Double longitude){
        return Address.builder().fullAddress(fullAddress).latitude(latitude).longitude(longitude).build();
    }
}
