package com.appcenter.wnt.domain.store;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@Embeddable
public class Owner {
    @Column(name = "user_id")
    private Long userId;

    public Owner(Long userId) {
        this.userId = userId;
    }
}
