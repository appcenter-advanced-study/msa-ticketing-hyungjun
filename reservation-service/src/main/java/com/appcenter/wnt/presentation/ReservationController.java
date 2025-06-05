package com.appcenter.wnt.presentation;

import com.appcenter.wnt.application.ReservationService;
import com.appcenter.wnt.application.dto.request.ReservationRequest;
import com.appcenter.wnt.application.dto.response.ReservationResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Reservation",description = "Reservation API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponse> reserve(@RequestBody ReservationRequest request) {
        ReservationResponse response = reservationService.reserve(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<String> cancel(@PathVariable("reservationId") Long reservationId) {
        return ResponseEntity.status(HttpStatus.OK).body("예매 취소 성공");
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<ReservationResponse>> getReservations(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(null);
    }
}
