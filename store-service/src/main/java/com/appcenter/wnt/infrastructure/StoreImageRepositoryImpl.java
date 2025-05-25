package com.appcenter.wnt.infrastructure;

import com.appcenter.wnt.domain.store.StoreImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StoreImageRepositoryImpl implements StoreImageRepository {
    private final StoreImageJpaRepository storeImageJpaRepository;


}
