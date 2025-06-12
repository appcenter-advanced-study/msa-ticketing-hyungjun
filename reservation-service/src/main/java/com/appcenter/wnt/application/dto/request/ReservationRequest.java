package com.appcenter.wnt.application.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record ReservationRequest(
        @Schema(example = "1")
        Long userId,
        @Schema(example = "1")
        Long storeId,
        @Schema(example = "1")
        Long menuId,
        ReservationTime reservationTime
) {

    public record ReservationTime(
            DayOfWeek dayOfWeek,

            @JsonFormat(pattern = "HH:mm")
            @Schema(example = "09:00")
            LocalTime reservationMinutes
    ) {
    }
}
