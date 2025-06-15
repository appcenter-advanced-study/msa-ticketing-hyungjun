package com.appcenter.wnt.domain.menu;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository {
    Optional<MenuItem> findById(Long id);
    MenuItem save(MenuItem menuItem);
    List<MenuItem> findByStoreId(Long storeId);
}
