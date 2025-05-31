package com.appcenter.wnt.infrastructure;

import com.appcenter.wnt.domain.store.StoreImage;
import com.appcenter.wnt.domain.store.StoreImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreImageRepositoryImpl implements StoreImageRepository {
    private final StoreImageJpaRepository jpaRepository;


    @Override
    public List<StoreImage> saveAll(List<StoreImage> images) {
        return jpaRepository.saveAll(images);
    }
}
