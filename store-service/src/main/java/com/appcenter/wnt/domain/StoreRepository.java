package com.appcenter.wnt.domain;

import java.util.List;
import java.util.Optional;

public interface StoreRepository {
    Optional<Store> findByStoreName(String storeName);
    Store save(Store store);
    List<Store> findAll();
    Optional<Store> findById(Long id);
    void delete(Store store);
}
