package com.appcenter.wnt.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Builder
    private Store(Long userId, String storeName) {
        this.owner = new Owner(userId);
        this.storeName = new StoreName(storeName);
    }

    public static Store of(Long userId, String storeName){
        return Store.builder().userId(userId).storeName(storeName).build();
    }
}
