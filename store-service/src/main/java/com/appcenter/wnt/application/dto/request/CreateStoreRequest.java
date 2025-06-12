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

public record CreateStoreRequest(
        @Schema(example = "1")
        Long userId,
        @Schema(example = "네일뭐해")
        String storeName,

        AddressDto address,

        List<BusinessHourDto> businessHours,
        ContactInfoDto contactInfo
) {

    public Store toEntity(){
        return Store.of(userId,
                storeName,
                address.toEntity(),
                businessHours.stream()
                        .map(BusinessHourDto::toEntity)
                        .toList(),
                contactInfo.toEntity());
    }

    public record BusinessHourDto(
            DayOfWeek dayOfWeek,

            @JsonFormat(pattern = "HH:mm")
            @Schema(example = "09:00")
            LocalTime startTime,

            @JsonFormat(pattern = "HH:mm")
            @Schema(example = "18:00")
            LocalTime endTime,

            @Schema(example = "60")
            int slotIntervalMinutes
    ) {
        public BusinessHour toEntity() {
            return BusinessHour.of(dayOfWeek, startTime, endTime,slotIntervalMinutes);
        }
    }

    public record ContactInfoDto(
            @Schema(example = "010-1111-2222")
            String phone,
            @Schema(example = "@strong_kang")
            String instagram
    ){
        public ContactInfo toEntity() {
            return ContactInfo.of(phone, instagram);
        }
    }

    public record AddressDto(
            @Schema(example = "서울광역시 중구 명동 10길 40 6층")
            String fullAddress,
            @Schema(example = "127.13245")
            double latitude,
            @Schema(example = "125.22135")
            double longitude
    ){
        public Address toEntity() {
            return Address.of(fullAddress, latitude, longitude);
        }
    }
}
