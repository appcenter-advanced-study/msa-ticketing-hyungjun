package com.appcenter.wnt.application;

import com.appcenter.wnt.application.dto.KakaoApproveResponse;
import com.appcenter.wnt.application.dto.KakaoReadyResponse;
import com.appcenter.wnt.application.dto.PaymentRequest;
import com.appcenter.wnt.application.properties.KakoPayProperties;
import com.appcenter.wnt.domain.Payment;
import com.appcenter.wnt.domain.PaymentApproveInfo;
import com.appcenter.wnt.domain.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class KakaoPayService {

    private final KakoPayProperties kakoPayProperties;
    private final RestTemplate restTemplate = new RestTemplate();
    private final PaymentRepository paymentRepository;
    private KakaoReadyResponse kakaoReadyResponse;

    private HttpHeaders getHeader(){
        HttpHeaders headers = new HttpHeaders();
        String auth = "SECRET_KEY " + kakoPayProperties.getSecretKey();
        headers.set("Authorization", auth);
        headers.set("Content-Type", "application/json");
        return headers;
    }

    public KakaoReadyResponse kakaoPayReady(PaymentRequest request){
        String reservationId = String.valueOf(request.reservationId());
        String userId = String.valueOf(request.userId());
        String itemName = request.storeName() +"-"+request.menuName();
        String price = String.valueOf(request.price());
        String vatAmount = String.valueOf(request.price()/10);
        log.info("부가세 : {}", vatAmount);

        Map<String, Object> params = new HashMap<>();
        params.put("cid", kakoPayProperties.getCid());
        params.put("partner_order_id",reservationId); // 주문 id
        params.put("partner_user_id",userId); // 사용자 id
        params.put("item_name",itemName); // 상품명
        params.put("quantity","1"); // 수량
        params.put("total_amount",price); // 총 금액
        params.put("vat_amount",vatAmount);  // 부과세
        params.put("tax_free_amount","0");  // 비과세
        params.put("approval_url","http://localhost:8080/api/payments/success");
        params.put("fail_url", "http://localhost:8080/api/payments/fail");
        params.put("cancel_url", "http://localhost:8080/api/payments/cancel");

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(params, this.getHeader());

        // 외부에 보낼 url
        kakaoReadyResponse = restTemplate.postForObject(
                "https://open-api.kakaopay.com/online/v1/payment/ready",
                requestEntity,
                KakaoReadyResponse.class);
        return kakaoReadyResponse;
    }

    public KakaoApproveResponse approveResponse(String pgToken, Long reservationId){
        Payment payment = paymentRepository.findByReservationId(reservationId).orElseThrow(()->new RuntimeException("결재 할 내역이 존재하지 않습니다."));

        // 카카오 요청
        Map<String, Object> params = new HashMap<>();
        params.put("cid", kakoPayProperties.getCid());
        params.put("tid",kakaoReadyResponse.getTid());
        params.put("partner_order_id",String.valueOf(reservationId));
        params.put("partner_user_id",String.valueOf(payment.getUserId()));
        params.put("pg_token", pgToken);

        // 헤더
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(params, this.getHeader());

        // 외부에 보낼 url
        KakaoApproveResponse approveResponse = restTemplate.postForObject(
                "https://open-api.kakaopay.com/online/v1/payment/approve",
                requestEntity,
                KakaoApproveResponse.class);

        String tid = approveResponse.getTid();
        String paymentMethodType = approveResponse.getPayment_method_type();
        LocalDateTime approvedAt = LocalDateTime.parse(approveResponse.getApproved_at());
        int totalAmount = approveResponse.getAmount().getTotal();

        payment.approve(new PaymentApproveInfo(tid,paymentMethodType,approvedAt,totalAmount));

        return approveResponse;
    }
}
