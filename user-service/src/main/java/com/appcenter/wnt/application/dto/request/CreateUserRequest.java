package com.appcenter.wnt.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateUserRequest(
        @Schema(example = "kang")
        String nickname
) {
}
