package com.appcenter.wnt.domain.menu;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class Price {
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 1_000_000;

    @Column(name = "price", nullable = false)
    private int price;

    public Price(int price){
        validate(price);
        this.price = price;
    }

    private void validate(int price){
        if(price < MIN_VALUE){
            throw new IllegalArgumentException("가격은 " + MIN_VALUE + "원 이상이어야 합니다.");
        }
        if(price >= MAX_VALUE){
            throw new IllegalArgumentException("가격은 " + (MAX_VALUE - 1) + "원 이하이어야 합니다.");
        }
    }

}
