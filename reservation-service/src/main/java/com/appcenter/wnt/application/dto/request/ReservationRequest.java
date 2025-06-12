package com.appcenter.wnt.application.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationRequest(
        @Schema(example = "1")
        Long userId,
        @Schema(example = "1")
        Long storeId,
        @Schema(example = "1")
        Long menuId,
        ReservationTimeDto reservationTime,
        ReservationDateDto reservationDate
) {

    public record ReservationTimeDto(
            DayOfWeek dayOfWeek,

            @JsonFormat(pattern = "HH:mm")
            @Schema(example = "09:00")
            LocalTime reservationTime
    ) {
    }

    public record ReservationDateDto(
            @Schema(example = "2025-06-12")
            LocalDate reservationDate
    ){
    }
}
