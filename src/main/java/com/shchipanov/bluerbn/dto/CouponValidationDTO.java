package com.shchipanov.bluerbn.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CouponValidationDTO {
    private int couponId;
    private boolean isCouponValid;
    private double price;
}
