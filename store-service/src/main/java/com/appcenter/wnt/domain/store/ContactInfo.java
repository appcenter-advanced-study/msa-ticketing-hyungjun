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
public class ContactInfo {
    @Column(name = "phone")
    private String phone;

    @Column(name = "instagram")
    private String instagram;

    public ContactInfo(String phone, String instagram) {
        this.phone = phone;
        this.instagram = instagram;
    }

    public static ContactInfo of(String phone, String instagram) {
        return ContactInfo.builder().phone(phone).instagram(instagram).build();
    }
}
