package com.appcenter.wnt.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class MenuInfo {

    @Column(name = "menu_id")
    private Long menuId;

    @Column(name = "price")
    private int price;

    @Column(name = "menu_name")
    private String menuName;

    public MenuInfo(Long menuId, int price, String menuName) {
        this.menuId = menuId;
        this.price = price;
        this.menuName = menuName;
    }
}
