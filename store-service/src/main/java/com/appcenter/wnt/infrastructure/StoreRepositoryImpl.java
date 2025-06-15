package com.appcenter.wnt.infrastructure;

import com.appcenter.wnt.domain.store.Store;
import com.appcenter.wnt.domain.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepository {
    private final StoreJpaRepository jpaRepository;

    @Override
    public Optional<Store> findByStoreName(String storeName) {
        return jpaRepository.findByStoreName(storeName);
    }

    @Override
    public Store save(Store store) {
        return jpaRepository.save(store);
    }

    @Override
    public List<Store> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<Store> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public void delete(Store store) {
        jpaRepository.delete(store);
    }
}
