package com.appcenter.wnt.domain;

import com.appcenter.wnt.domain.enums.NailCategory;
import com.appcenter.wnt.domain.enums.NailReservationTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="nail_reservations",
        uniqueConstraints = @UniqueConstraint(name="uq_store_date_time",
                columnNames = {"store_id", "nail_reservation_date","nail_reservation_time"})) // jpa를 통해 unique를 걸어줄 수 있다.

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nail_reservation_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "store_name")
    private String storeName;

    @Enumerated(EnumType.STRING)
    @Column(name = "nail_category")
    private NailCategory nailCategory;

    @Column(name = "nail_reservation_date")
    private LocalDate reservationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "nail_reservation_time")
    private NailReservationTime reservationTime;

    @Builder
    private Reservation(Long userId, String userName, Long storeId, String storeName, NailCategory nailCategory, LocalDate reservationDate, NailReservationTime reservationTime) {
        this.userId = userId;
        this.userName = userName;
        this.storeId = storeId;
        this.storeName = storeName;
        this.nailCategory = nailCategory;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
    }

    public static Reservation of(Long userId, String userName, Long storeId, String storeName, NailCategory nailCategory, LocalDate reservationDate, NailReservationTime reservationTime) {
        return Reservation.builder()
                .userId(userId)
                .userName(userName)
                .storeId(storeId)
                .storeName(storeName)
                .nailCategory(nailCategory)
                .reservationDate(reservationDate)
                .reservationTime(reservationTime).build();
    }
}
