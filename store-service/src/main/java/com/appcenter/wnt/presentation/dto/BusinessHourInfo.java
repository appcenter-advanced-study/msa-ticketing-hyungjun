package com.appcenter.wnt.presentation.dto;

import lombok.Builder;

import java.time.LocalTime;
import java.util.List;

@Builder
public record BusinessHourInfo(
        List<LocalTime> businessTimes
) {
    public static BusinessHourInfo from(List<LocalTime> businessTimes) {
        return BusinessHourInfo.builder()
                .businessTimes(businessTimes)
                .build();
    }
}
