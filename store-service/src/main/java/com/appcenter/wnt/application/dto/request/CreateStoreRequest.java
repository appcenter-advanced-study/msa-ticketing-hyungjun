package com.appcenter.wnt.application.dto.request;

import com.appcenter.wnt.domain.store.Address;
import com.appcenter.wnt.domain.store.BusinessHour;
import com.appcenter.wnt.domain.store.ContactInfo;
import com.appcenter.wnt.domain.store.Store;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public record CreateStoreRequest(
        Long userId,
        String storeName,
        Address address,
        List<BusinessHourDto> businessHours,
        ContactInfo contactInfo
) {

    public Store toEntity(){
        return Store.of(userId,
                storeName,
                address,
                businessHours.stream()
                        .map(BusinessHourDto::toEntity)
                        .collect(Collectors.toSet()),
                contactInfo);
    }
    public record BusinessHourDto(
            DayOfWeek dayOfWeek,

            @JsonFormat(pattern = "HH:mm")
            @Schema(example = "09:00")
            LocalTime startTime,

            @JsonFormat(pattern = "HH:mm")
            @Schema(example = "18:00")
            LocalTime endTime,

            int slotIntervalMinutes
    ) {
        public BusinessHour toEntity() {
            return BusinessHour.of(dayOfWeek, startTime, endTime,slotIntervalMinutes);
        }
    }
}
