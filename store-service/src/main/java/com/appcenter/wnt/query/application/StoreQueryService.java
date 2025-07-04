package com.appcenter.wnt.query.application;

import com.appcenter.wnt.application.dto.response.StoreDetailPageResponse;
import com.appcenter.wnt.application.dto.response.StoreResponse;
import com.appcenter.wnt.domain.store.BusinessHour;
import com.appcenter.wnt.domain.store.Store;
import com.appcenter.wnt.domain.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryService {
    private final StoreRepository storeRepository;

    public StoreDetailPageResponse getStoreDetails(Long storeId){
        Store store = storeRepository.findById(storeId).orElseThrow(()-> new RuntimeException("가게가 존재하지 않습니다."));
        // 영업 날짜 별 영업 종료 시간 구하기
        DayOfWeek dayOfWeek = DayOfWeek.of(Calendar.DAY_OF_WEEK);

        Optional<BusinessHour> businessHour = store.getBusinessHours().stream().filter(bh -> dayOfWeek.equals(bh.getDayOfWeek())).findFirst();
        if(businessHour.isEmpty()){
            throw new RuntimeException("날짜가 맞지 않습니다. 날짜를 등록해주세요");
        }

        return StoreDetailPageResponse.from(store, businessHour.get().getEndTime());
    }

    public StoreResponse findStore(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new RuntimeException("가게가 존재하지 않습니다."));
        return StoreResponse.from(store);
    }

    public List<StoreResponse> findAllStore() {
        return storeRepository.findAll().stream().map(StoreResponse::from).collect(Collectors.toList());
    }


}
