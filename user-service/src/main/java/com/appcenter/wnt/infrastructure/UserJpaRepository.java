package com.appcenter.wnt.infrastructure;

import com.appcenter.wnt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
