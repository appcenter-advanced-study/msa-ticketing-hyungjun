package com.appcenter.wnt.query.repository;

import com.appcenter.wnt.application.dto.response.StoreDetailPageResponse;
import com.appcenter.wnt.query.domain.StoreQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreQueryRepository extends JpaRepository<StoreQuery, Long> {
}
