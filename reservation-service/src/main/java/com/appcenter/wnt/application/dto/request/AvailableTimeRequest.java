package com.appcenter.wnt.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record AvailableTimeRequest(
        Long storeId,

        @Schema(example = "2025-06-12")
        LocalDate reservationDate
) {
}
