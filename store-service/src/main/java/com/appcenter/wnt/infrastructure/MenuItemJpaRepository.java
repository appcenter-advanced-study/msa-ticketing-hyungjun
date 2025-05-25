package com.appcenter.wnt.infrastructure;

import com.appcenter.wnt.domain.menu.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemJpaRepository extends JpaRepository<MenuItem, Long> {
}
