package com.appcenter.wnt.application.dto.request;

import com.appcenter.wnt.domain.enums.NailCategory;
import com.appcenter.wnt.domain.enums.NailReservationTime;

import java.time.LocalDate;

public record ReservationRequest(
        Long userId,
        Long storeId,
        NailCategory nailCategory,
        LocalDate reservationDate,
        NailReservationTime reservationTime
) {
}
