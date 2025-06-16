package com.appcenter.wnt.presentation;

import com.appcenter.wnt.application.KakaoPayService;
import com.appcenter.wnt.application.dto.KakaoApproveResponse;
import com.appcenter.wnt.application.dto.KakaoReadyResponse;
import com.appcenter.wnt.application.dto.PaymentRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Payment",description = "Payment API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments/kakao")
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;

    //결제 요청
    @PostMapping("/ready")
    public KakaoReadyResponse readyToKakaoPay(@RequestBody PaymentRequest request){
        return kakaoPayService.kakaoPayReady(request);
    }

    // 결제 성공
    @PostMapping("/success")
    public ResponseEntity<KakaoApproveResponse> afterPayRequest(@RequestParam("pgToken") String pgToken,
                                                                @RequestParam("reservationId") Long reservationId){
        KakaoApproveResponse kakaoApproveResponse = kakaoPayService.approveResponse(pgToken,reservationId);
        return new ResponseEntity<>(kakaoApproveResponse, HttpStatus.OK);
    }

    @GetMapping("/cancel")
    public void cancelPayment(){
        throw new RuntimeException("결제 취소");
    }

    @GetMapping("/fail")
    public void failPayment(){
        throw new RuntimeException("결제 실패");
    }
}
