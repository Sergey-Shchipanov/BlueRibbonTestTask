package com.shchipanov.bluerbn.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Coupon {

    private int couponId;
    private int discountPercent;
}
