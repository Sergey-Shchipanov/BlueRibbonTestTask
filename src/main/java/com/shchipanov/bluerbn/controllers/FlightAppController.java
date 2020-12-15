package com.shchipanov.bluerbn.controllers;

import com.shchipanov.bluerbn.dto.CouponValidationDTO;
import com.shchipanov.bluerbn.services.BaggageCheckInService;
import com.shchipanov.bluerbn.services.CouponValidationService;
import com.shchipanov.bluerbn.services.TicketCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/flightApp")
public class FlightAppController {

    @Autowired
    private CouponValidationService couponValidationService;

    @Autowired
    private TicketCheckService ticketCheckService;

    @Autowired
    private BaggageCheckInService baggageCheckInService;

    @GetMapping(path = "/ticketAvailable")
    public boolean checkTicketAvailable(@RequestParam int ticketId) {

        return false;
    }

    @PostMapping(path = "/baggageCheckIn")
    public boolean baggageCheckIn(@RequestParam int destinationId, @RequestParam String baggageId) {

        return false;
    }

    @GetMapping(path = "/validateCoupon")
    public CouponValidationDTO validateCoupon(@RequestParam int couponId, @RequestParam double price) {

        return new CouponValidationDTO();
    }
}
