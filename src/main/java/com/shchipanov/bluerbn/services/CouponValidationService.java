package com.shchipanov.bluerbn.services;

import com.shchipanov.bluerbn.dao.Coupon;
import com.shchipanov.bluerbn.dto.CouponValidationDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CouponValidationService {

    @Autowired
    CacheService cacheService;

    @Autowired
    DataAccessService dataAccessService;

    public CouponValidationDTO validateCoupon(int couponId, double originalPrice) {
        log.debug("Validating coupon " + couponId);

        if (cacheService.containsCouponCache(couponId)) {
            return cacheService.getCouponValidationResultFromCache(couponId);
        }
        Coupon couponFromDataSource = dataAccessService.getCouponById(couponId);

        if (couponFromDataSource.getCouponId() == couponId) {
            CouponValidationDTO result = new CouponValidationDTO();
            result.setCouponId(couponId);
            result.setPrice(originalPrice / 100 * (100 - couponFromDataSource.getDiscountPercent()));
            result.setCouponValid(true);

            cacheService.addToCouponsCache(couponId, result);
            return result;
        } else {
            CouponValidationDTO result = new CouponValidationDTO();
            result.setCouponId(couponId);
            result.setPrice(originalPrice);
            result.setCouponValid(false);
            cacheService.addToCouponsCache(couponId, result);
            return result;
        }
    }
}
