package com.appcenter.wnt.application;

import com.appcenter.wnt.application.dto.KakaoApproveResponse;
import com.appcenter.wnt.application.dto.KakaoReadyResponse;
import com.appcenter.wnt.domain.KakoPayProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class KakaoPayService {

    private final KakoPayProperties kakoPayProperties;
    private RestTemplate restTemplate = new RestTemplate();
    private KakaoReadyResponse kakaoReadyResponse;

    private HttpHeaders getHeader(){
        HttpHeaders headers = new HttpHeaders();
        String auth = "SECRET_KEY " + kakoPayProperties.getSecretKey();
        headers.set("Authorization", auth);
        headers.set("Content-Type", "application/json");
        return headers;
    }

    public KakaoReadyResponse kakaoPayReady(){
        Map<String, Object> params = new HashMap<>();
        params.put("cid", kakoPayProperties.getCid());
        params.put("partner_order_id","ORDER_ID"); // 주문 id
        params.put("partner_user_id","USER_ID"); // 사용자 id
        params.put("item_name","ITEM_NAME"); // 상품명
        params.put("quantity","1"); // 수량
        params.put("total_amount","30000"); // 총 금액
        params.put("vat_amount","3000");  // 부과세
        params.put("tax_free_amount","0");  // 비과세
        params.put("approval_url","http://localhost:8080/api/payments/success");
        params.put("fail_url", "http://localhost:8080/api/payments/fail");
        params.put("cancel_url", "http://localhost:8080/api/payments/cancel");

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(params, this.getHeader());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();
        kakaoReadyResponse = restTemplate.postForObject(
                "https://open-api.kakaopay.com/online/v1/payment/ready",
                requestEntity,
                KakaoReadyResponse.class);
        return kakaoReadyResponse;
    }

    public KakaoApproveResponse approveResponse (String pgToken){
        // 카카오 요청
        Map<String, Object> params = new HashMap<>();
        params.put("cid", kakoPayProperties.getCid());
        params.put("tid",kakaoReadyResponse.getTid());
        params.put("partner_order_id","ORDER_ID");
        params.put("partner_user_id","USER_ID");
        params.put("pg_token", pgToken);

        // 헤더
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(params, this.getHeader());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        KakaoApproveResponse approveResponse = restTemplate.postForObject(
                "https://open-api.kakaopay.com/online/v1/payment/approve",
                requestEntity,
                KakaoApproveResponse.class);

        return approveResponse;
    }
}
