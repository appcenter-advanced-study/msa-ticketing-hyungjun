package com.appcenter.wnt.domain.store;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Embeddable
@Getter
@NoArgsConstructor
@Builder
public class BusinessHour {

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "slot_interval_minutes",nullable = false)
    private int slotIntervalMinutes;

    public BusinessHour(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime, int slotIntervalMinutes) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.slotIntervalMinutes = slotIntervalMinutes;
    }

    public static BusinessHour of(DayOfWeek dayOfWeek,LocalTime startTime, LocalTime endTime, int slotIntervalMinutes) {
        return BusinessHour.builder().dayOfWeek(dayOfWeek).startTime(startTime).endTime(endTime).slotIntervalMinutes(slotIntervalMinutes).build();
    }
}
