package com.appcenter.wnt.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Amount {
    private int total;
    private int tax;
    private int point;
    private int discount;
    private int green_deposit;
}
