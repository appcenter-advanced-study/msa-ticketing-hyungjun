package com.appcenter.wnt.presentation;

import com.appcenter.wnt.application.ReservationInfoService;
import com.appcenter.wnt.application.StoreService;
import com.appcenter.wnt.application.dto.response.StoreResponse;
import com.appcenter.wnt.presentation.dto.ReservationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internal/stores")
@RequiredArgsConstructor
public class StoreInternalController {
    private final StoreService storeService;
    private final ReservationInfoService reservationInfoService;

    // 가게 조회(내부 조회)
    @GetMapping("/{storeId}")
    public StoreResponse getInternalStore(@PathVariable("storeId") Long storeId) {
        return storeService.findStore(storeId);
    }

    @GetMapping("/reservation")
    public ReservationInfo getReservationInfo(@RequestParam("storeId") Long storeId, @RequestParam("menuItemId") Long menuItemId) {
        return reservationInfoService.findReservationInfo(storeId, menuItemId);
    }
}
