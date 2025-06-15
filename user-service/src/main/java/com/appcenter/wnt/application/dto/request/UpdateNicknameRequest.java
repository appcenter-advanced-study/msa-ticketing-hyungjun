package com.appcenter.wnt.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateNicknameRequest(
        @Schema(example = "strong_kang")
        String nickname
) {
}
