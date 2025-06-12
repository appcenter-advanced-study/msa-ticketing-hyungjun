package com.appcenter.wnt.application.dto.response;

import lombok.Builder;

import java.time.LocalTime;
import java.util.List;

@Builder
public record AvailableTimeResponse(
        List<LocalTime> businessTimes,
        List<LocalTime> availableTimes
) {
    public static AvailableTimeResponse of(List<LocalTime> businessTimes, List<LocalTime> availableTimes) {
        return AvailableTimeResponse.builder()
                .businessTimes(businessTimes)
                .availableTimes(availableTimes)
                .build();
    }
}
