package com.appcenter.wnt.application;

import com.appcenter.wnt.application.dto.request.CreateMenuRequest;
import com.appcenter.wnt.application.dto.response.MenuItemResponse;
import com.appcenter.wnt.domain.menu.MenuItem;
import com.appcenter.wnt.domain.menu.MenuItemRepository;
import com.appcenter.wnt.domain.store.Store;
import com.appcenter.wnt.domain.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuItemService {
    private final StoreRepository storeRepository;
    private final MenuItemRepository menuItemRepository;

    public MenuItemResponse createMenu(CreateMenuRequest request) {
        Store store = storeRepository.findById(request.storeId()).orElseThrow(()-> new RuntimeException("예외처리"));
        MenuItem menuItem = menuItemRepository.save(request.toEntity(store));
        return MenuItemResponse.from(menuItem);
    }
}
