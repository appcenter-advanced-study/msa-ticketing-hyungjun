package com.appcenter.wnt.infrastructure.event;

public record ReservationStatusEvent (
        Long reservationId,
        boolean isApprove
){
}
