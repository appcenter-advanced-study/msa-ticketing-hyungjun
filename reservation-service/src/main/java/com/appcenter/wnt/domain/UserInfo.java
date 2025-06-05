package com.appcenter.wnt.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class UserInfo {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    public UserInfo(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
