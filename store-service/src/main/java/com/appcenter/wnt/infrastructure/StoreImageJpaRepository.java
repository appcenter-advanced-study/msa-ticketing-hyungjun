package com.appcenter.wnt.infrastructure;

import com.appcenter.wnt.domain.store.StoreImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreImageJpaRepository extends JpaRepository<StoreImage, Long> {
}
