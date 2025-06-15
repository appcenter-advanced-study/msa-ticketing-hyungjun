package com.appcenter.wnt.infrastructure.dto.response;

import java.time.LocalTime;
import java.util.List;

public record BusinessHourResponse(
        List<LocalTime> businessTimes
) {
}
