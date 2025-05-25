package com.appcenter.wnt.infrastructure;

import com.appcenter.wnt.domain.menu.MenuItem;
import com.appcenter.wnt.domain.menu.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MenuItemRepositoryImpl implements MenuItemRepository {
    private final MenuItemJpaRepository jpaRepository;

    @Override
    public Optional<MenuItem> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public MenuItem save(MenuItem menuItem) {
        return jpaRepository.save(menuItem);
    }
}
