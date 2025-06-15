package com.appcenter.wnt.application;

import com.appcenter.wnt.domain.menu.MenuItem;
import com.appcenter.wnt.domain.menu.MenuItemRepository;
import com.appcenter.wnt.domain.store.Store;
import com.appcenter.wnt.domain.store.StoreRepository;
import com.appcenter.wnt.presentation.dto.ReservationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationInfoService {
    private final MenuItemRepository menuItemRepository;
    private final StoreRepository storeRepository;

    public ReservationInfo findReservationInfo(Long storeId, Long menuItemId) {
        Store store = storeRepository.findById(storeId).orElseThrow(()-> new RuntimeException("존재하지 않은 가게입니다."));
        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElseThrow(()-> new RuntimeException("존재하지 않는 메뉴입니다."));

        return ReservationInfo.of(store, menuItem);
    }

}
