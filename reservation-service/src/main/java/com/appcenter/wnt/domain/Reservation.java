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
                        columnNames = {"store_id", "menu_id", "day_of_week","reservation_time"}
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

    @Builder
    private Reservation(UserInfo userInfo, MenuInfo menuInfo, StoreInfo storeInfo, ReservationTime reservationTime) {
        this.userInfo = userInfo;
        this.menuInfo = menuInfo;
        this.storeInfo = storeInfo;
        this.reservationTime = reservationTime;
    }

    public static Reservation of(UserInfo userInfo, MenuInfo menuInfo, StoreInfo storeInfo, ReservationTime reservationTime) {
        return Reservation.builder().userInfo(userInfo).menuInfo(menuInfo).storeInfo(storeInfo).reservationTime(reservationTime).build();
    }
}
