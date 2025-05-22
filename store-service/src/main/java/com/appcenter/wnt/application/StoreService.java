package com.appcenter.wnt.application;

import com.appcenter.wnt.infrastructure.UserServiceClient;
import com.appcenter.wnt.infrastructure.dto.response.UserResponse;
import com.appcenter.wnt.domain.Store;
import com.appcenter.wnt.application.dto.response.StoreResponse;
import com.appcenter.wnt.domain.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final UserServiceClient userServiceClient;

    @Transactional
    public StoreResponse createStore(Long userId, String storeName){
        UserResponse user = userServiceClient.getUserById(userId);
        storeRepository.findByStoreName(storeName).ifPresent(n ->{
            throw new RuntimeException("이미 존재하는 가게입니다.");
        });

        Store store = storeRepository.save(Store.of(user.id(), storeName));
        return StoreResponse.from(store);
    }

    @Transactional(readOnly = true)
    public StoreResponse findStore(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new RuntimeException("가게가 존재하지 않습니다."));
        return StoreResponse.from(store);
    }

    @Transactional(readOnly = true)
    public List<StoreResponse> findAllStore() {
        return storeRepository.findAll().stream().map(StoreResponse::from).collect(Collectors.toList());
    }

    @Transactional
    public void deleteStore(Long storeId){
        Store store = storeRepository.findById(storeId).orElseThrow(()-> new RuntimeException("존재하지 않은 가게입니다."));
        storeRepository.delete(store);
    }
}
