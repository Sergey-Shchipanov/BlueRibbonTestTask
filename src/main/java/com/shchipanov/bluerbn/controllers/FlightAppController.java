package com.shchipanov.bluerbn.controllers;

import com.shchipanov.bluerbn.dto.CouponValidationDTO;
import com.shchipanov.bluerbn.services.BaggageCheckInService;
import com.shchipanov.bluerbn.services.CouponValidationService;
import com.shchipanov.bluerbn.services.TicketCheckService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("flightApp")
public class FlightAppController {

    @Autowired
    private CouponValidationService couponValidationService;

    @Autowired
    private TicketCheckService ticketCheckService;

    @Autowired
    private BaggageCheckInService baggageCheckInService;

    @GetMapping("/ticketAvailable")
    public boolean checkTicketAvailable(@RequestParam Integer ticketId) {
        log.debug(String.format("Check ticket: %d is valid", ticketId));
        boolean result = ticketCheckService.checkTicketAvailable(ticketId);
        log.debug(String.format("Check ticket: %d is valid = %b", ticketId, result));
        return result;
    }

    @PostMapping("/baggageCheckIn")
    public boolean baggageCheckIn(@RequestParam int destinationId, @RequestParam String baggageId) {
        log.debug(String.format("Check in baggage %s to destination %d", baggageId, destinationId));
        boolean result = baggageCheckInService.checkInBaggage(baggageId, destinationId);
        log.debug("Check in result: " + result);
        return result;
    }

    @GetMapping("/validateCoupon")
    public CouponValidationDTO validateCoupon(@RequestParam int couponId, @RequestParam double price) {

        return new CouponValidationDTO();
    }
}
