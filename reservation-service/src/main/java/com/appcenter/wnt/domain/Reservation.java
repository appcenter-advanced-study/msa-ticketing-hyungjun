package com.appcenter.wnt.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name="reservations",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_reservation_store_menu_date_time",
                        columnNames = {"store_id", "day_of_week", "reservation_time", "reservation_date"}
                )

})
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @Embedded
    private UserInfo userInfo;

    @Embedded
    private MenuInfo menuInfo;

    @Embedded
    private StoreInfo storeInfo;

    @Embedded
    private ReservationTime reservationTime;

    @Embedded
    private ReservationDate reservationDate;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @Builder
    private Reservation(UserInfo userInfo, MenuInfo menuInfo, StoreInfo storeInfo, ReservationTime reservationTime, ReservationDate reservationDate) {
        this.userInfo = userInfo;
        this.menuInfo = menuInfo;
        this.storeInfo = storeInfo;
        this.reservationTime = reservationTime;
        this.reservationDate = reservationDate;
        this.reservationStatus = ReservationStatus.PENDING;
    }

    public static Reservation of(UserInfo userInfo, MenuInfo menuInfo, StoreInfo storeInfo, ReservationTime reservationTime, ReservationDate reservationDate) {
        return Reservation.builder().userInfo(userInfo).menuInfo(menuInfo).storeInfo(storeInfo).reservationTime(reservationTime).reservationDate(reservationDate).build();
    }

    public void approve() {
        this.reservationStatus = ReservationStatus.APPROVE;
    }

    public void cancel(){
        this.reservationStatus = ReservationStatus.CANCEL;
    }
}
