package com.shchipanov.bluerbn.services;

import com.shchipanov.bluerbn.dao.Coupon;
import com.shchipanov.bluerbn.dao.Destination;
import com.shchipanov.bluerbn.dao.Ticket;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    public Ticket getTicketById(int ticketId) {

        return new Ticket();
    }

    public Coupon getCouponById(int couponId) {

        return new Coupon();
    }

    public Destination getDestinationById(int destinationId) {

        return new Destination();
    }
}
