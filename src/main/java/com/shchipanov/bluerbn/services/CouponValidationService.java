package com.shchipanov.bluerbn.services;

import com.shchipanov.bluerbn.dto.CouponValidationDTO;
import org.springframework.stereotype.Service;

@Service
public class CouponValidationService {

    public CouponValidationDTO validateCoupon(int couponId, double originalPrice) {

        var result = new CouponValidationDTO();

        result.setCouponId(couponId);
        result.setCouponValid(false);
        result.setPrice(originalPrice);

        return result;
    }
}
