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

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "store_name")
    private String name;

    @Builder
    private Store(Long userId, String name){
        this.userId = userId;
        this.name = name;
    }

    public static Store of(Long userId, String name){
        return Store.builder().userId(userId).name(name).build();
    }
}
