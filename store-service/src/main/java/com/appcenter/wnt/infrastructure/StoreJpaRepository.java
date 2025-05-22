package com.appcenter.wnt.infrastructure;

import com.appcenter.wnt.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StoreJpaRepository extends JpaRepository<Store, Long> {
    @Query("select s from Store s where s.storeName.storeName = :storeName")
    Optional<Store> findByStoreName(String storeName);
}
