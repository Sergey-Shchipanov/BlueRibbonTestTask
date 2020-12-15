package com.shchipanov.bluerbn.services;

import com.shchipanov.bluerbn.dao.Coupon;
import com.shchipanov.bluerbn.dao.Destination;
import com.shchipanov.bluerbn.dao.Ticket;

public interface DataAccessService {

    Ticket getTicketById(int ticketId);

    Coupon getCouponById(int couponId);

    Destination getDestinationById(int destinationId);
}
