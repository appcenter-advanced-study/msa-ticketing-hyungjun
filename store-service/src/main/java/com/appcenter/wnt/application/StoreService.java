package com.appcenter.wnt.application;

import com.appcenter.wnt.application.dto.request.CreateStoreRequest;
import com.appcenter.wnt.application.dto.response.StoreDetailPageResponse;
import com.appcenter.wnt.application.dto.response.StoreResponse;
import com.appcenter.wnt.domain.store.BusinessHour;
import com.appcenter.wnt.domain.store.Store;
import com.appcenter.wnt.domain.store.StoreRepository;
import com.appcenter.wnt.infrastructure.UserServiceClient;
import com.appcenter.wnt.infrastructure.dto.response.UserResponse;
import com.appcenter.wnt.presentation.dto.BusinessHourInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {
    private final StoreRepository storeRepository;
    private final UserServiceClient userServiceClient;

    @Transactional
    public StoreResponse createStore(CreateStoreRequest request){
        UserResponse user = userServiceClient.getUserById(request.userId());
        storeRepository.findByStoreName(request.storeName()).ifPresent(n ->{
            throw new RuntimeException("이미 존재하는 가게입니다.");
        });

        Store store = storeRepository.save(request.toEntity());
        return StoreResponse.from(store);
    }

//    public StoreDetailPageResponse getStoreDetails(Long storeId){
//        Store store = storeRepository.findById(storeId).orElseThrow(()-> new RuntimeException("가게가 존재하지 않습니다."));
//        // 영업 날짜 별 영업 종료 시간 구하기
//        DayOfWeek dayOfWeek = DayOfWeek.of(Calendar.DAY_OF_WEEK);
//
//        Optional<BusinessHour> businessHour = store.getBusinessHours().stream().filter(bh -> dayOfWeek.equals(bh.getDayOfWeek())).findFirst();
//        if(businessHour.isEmpty()){
//            throw new RuntimeException("날짜가 맞지 않습니다. 날짜를 등록해주세요");
//        }
//
//        return StoreDetailPageResponse.from(store, businessHour.get().getEndTime());
//    }
//
//    public StoreResponse findStore(Long storeId) {
//        Store store = storeRepository.findById(storeId).orElseThrow(() -> new RuntimeException("가게가 존재하지 않습니다."));
//        return StoreResponse.from(store);
//    }
//
//    public List<StoreResponse> findAllStore() {
//        return storeRepository.findAll().stream().map(StoreResponse::from).collect(Collectors.toList());
//    }

    @Transactional
    public void deleteStore(Long storeId){
        Store store = storeRepository.findById(storeId).orElseThrow(()-> new RuntimeException("존재하지 않은 가게입니다."));
        storeRepository.delete(store);
    }

    public BusinessHourInfo getBusinessHourInfo(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(()-> new RuntimeException("존재하지 않은 가게입니다."));
        Optional<BusinessHour> optionalBusinessHour = store.getBusinessHours().stream().findAny();
        if(optionalBusinessHour.isEmpty()){
            throw new RuntimeException("영업 시간이 존재하지 않습니다. 영업 시간을 입력해주세요");
        }

        // 예약 가능한 시간 계산
        List<LocalTime> businessTimes = getBusinessTimes(optionalBusinessHour.get());

        return BusinessHourInfo.from(businessTimes);
    }

    private static List<LocalTime> getBusinessTimes(BusinessHour businessHour) {
        LocalTime startTime = businessHour.getStartTime();
        LocalTime endTime = businessHour.getEndTime();
        int slotIntervalMinutes = businessHour.getSlotIntervalMinutes();
        log.info("startTime : {}, endTime : {}, slot : {}", startTime,endTime, slotIntervalMinutes);

        List<LocalTime> businessTimes = new ArrayList<>();

        while(!startTime.isAfter(endTime)){
            businessTimes.add(startTime);
            startTime = startTime.plusMinutes(slotIntervalMinutes);
        }
        return businessTimes;
    }
}
