package com.appcenter.wnt.domain.store;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(nullable = false)
    @Embedded
    private Owner owner;

    @Column(nullable = false, unique = true)
    @Embedded
    private StoreName storeName;

    @Column(nullable = false)
    @Embedded
    private Address address;

    @ElementCollection
    @CollectionTable(name = "business_hours",joinColumns = @JoinColumn(name = "store_id"))
    private Set<BusinessHour> businessHours;

    @Column(nullable = false)
    @Embedded
    private ContactInfo contactInfo;

    @Enumerated(EnumType.STRING)
    private StoreStatus status;

    @Builder
    private Store(Long userId, String storeName, Address address, Set<BusinessHour> businessHours, ContactInfo contactInfo) {
        this.owner = new Owner(userId);
        this.storeName = new StoreName(storeName);
        this.address = address;
        this.businessHours = businessHours;
        this.contactInfo = contactInfo;
        this.status = StoreStatus.SUSPENDED;
    }

    public static Store of(Long userId, String storeName, Address address, Set<BusinessHour> businessHours) {
        return Store.builder().userId(userId).storeName(storeName).address(address).businessHours(businessHours).build();
    }
}
