package com.appcenter.wnt.application;

import com.appcenter.wnt.infrastructure.UserServiceClient;
import com.appcenter.wnt.infrastructure.dto.response.UserResponse;
import com.appcenter.wnt.domain.Coupon;
import com.appcenter.wnt.domain.CouponReservation;
import com.appcenter.wnt.domain.CouponStock;
import com.appcenter.wnt.domain.CouponType;
import com.appcenter.wnt.application.dto.response.CouponReservationDetailResponse;
import com.appcenter.wnt.application.dto.response.CouponReservationResponse;
import com.appcenter.wnt.domain.CouponRepository;
import com.appcenter.wnt.domain.CouponReservationRepository;
import com.appcenter.wnt.domain.CouponStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponReservationService {
    private final CouponStockRepository couponStockRepository;
    private final CouponRepository couponRepository;
    private final CouponReservationRepository reservationRepository;
    private final UserServiceClient userClient;

    @Transactional
    public CouponReservationDetailResponse reserve(Long userId, Long couponId) {
        UserResponse user = userClient.getUserById(userId);
        Coupon coupon = couponRepository.findById(couponId).orElseThrow(()-> new RuntimeException("쿠폰이 존재하지 않습니다."));
        CouponStock couponStock = couponStockRepository.findByIdWithPessimisticLock(coupon.getId()).orElseThrow(()-> new RuntimeException("쿠폰 재고가 존재하지 않습니다."));
        reservationRepository.findByUserIdAndCouponId(userId,couponId).ifPresent(cr ->{
            throw new RuntimeException("이미 존재하는 쿠폰입니다.");
        });
        couponStock.decreaseQuantity();
        CouponReservation couponReservation = reservationRepository.save(CouponReservation.of(user.id(), coupon));

        return CouponReservationDetailResponse.of(coupon.getId(),user.id(), couponReservation.getId(), coupon.getType().name(),coupon.getType().getDescription(), couponStock.getQuantity());
    }

    @Transactional
    public void cancelReservation(Long userId, Long couponId) {
        //UserResponse user = userClient.getUserById(userId);
        Coupon coupon = couponRepository.findById(couponId).orElseThrow(()-> new RuntimeException("쿠폰이 존재하지 않습니다."));
        CouponStock couponStock = couponStockRepository.findByCouponId(coupon.getId()).orElseThrow(()-> new RuntimeException("쿠폰 재고가 존재하지 않습니다."));
        CouponReservation couponReservation = reservationRepository.findByUserIdAndCouponId(userId, couponId).orElseThrow(()-> new RuntimeException("쿠폰 에매가 존재하지 않습니다."));
        couponStock.increaseQuantity();

        reservationRepository.delete(couponReservation);
    }

    @Transactional
    public List<CouponReservationResponse> getUserReservations(Long userId) {
        UserResponse user = userClient.getUserById(userId);
        List<CouponReservation> couponReservations  = reservationRepository.findByUserId(user.id());

        return couponReservations.stream().map(couponReservation -> {
            CouponType couponType = couponReservation.getCoupon().getType();
            return CouponReservationResponse.of(
                    user.id(),
                    couponReservation.getId(),
                    couponType.name(),
                    couponType.getDescription());
        }).toList();
    }
}
