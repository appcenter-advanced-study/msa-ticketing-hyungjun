package com.appcenter.wnt.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@Embeddable
public class ReservationTime {

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week")
    private DayOfWeek dayOfWeek;

    @Column(name = "reservation_time")
    private LocalTime reservationTime;

    public ReservationTime(DayOfWeek dayOfWeek, LocalTime reservationTime) {
        this.dayOfWeek = dayOfWeek;
        this.reservationTime = reservationTime;
    }
}
