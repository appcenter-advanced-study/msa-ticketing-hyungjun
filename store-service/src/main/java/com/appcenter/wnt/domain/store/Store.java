package com.appcenter.wnt.domain.store;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
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
    private List<BusinessHour> businessHours;

    @Column(nullable = false)
    @Embedded
    private ContactInfo contactInfo;

    @Enumerated(EnumType.STRING)
    private StoreStatus status;

    @ElementCollection
    @CollectionTable(
            name = "store_thumbnail_images",
            joinColumns = @JoinColumn(name = "store_id")
    )
    @Column(name = "thumbnail_image_ids")
    @Size(max = 4)
    private List<Long> thumbnailImageIds;

    @Column(name = "rating")
    private double rating;

    @Column(name = "review_count")
    private long reviewCount;

    @Builder
    private Store(Long userId, String storeName, Address address, List<BusinessHour> businessHours, ContactInfo contactInfo) {
        this.owner = new Owner(userId);
        this.storeName = new StoreName(storeName);
        this.address = address;
        this.businessHours = businessHours;
        this.contactInfo = contactInfo;
        this.status = StoreStatus.SUSPENDED;
        this.thumbnailImageIds = new ArrayList<>();
        this.rating = 0.0;
        this.reviewCount = 0;
    }

    public static Store of(Long userId, String storeName, Address address, List<BusinessHour> businessHours, ContactInfo contactInfo) {
        return Store.builder().userId(userId).storeName(storeName).address(address).businessHours(businessHours).contactInfo(contactInfo).build();
    }
}
