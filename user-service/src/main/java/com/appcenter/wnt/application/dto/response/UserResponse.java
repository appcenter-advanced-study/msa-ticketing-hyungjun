package com.appcenter.wnt.application.dto.response;

import com.appcenter.wnt.domain.User;
import lombok.Builder;

@Builder
public record UserResponse(
        Long id,
        String nickname
) {
    public static UserResponse from(User user) {
        return UserResponse.builder().id(user.getId()).nickname(user.getNickname().getNickname()).build();
    }
}
