package com.shchipanov.bluerbn.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class HardcodedDataAccessServiceTest {

    @Test
    void checkTicketIdExist() {
        assertEquals(101, new HardcodedDataAccessService().getTicketById(101).getTicketId());
        assertNotEquals(1, new HardcodedDataAccessService().getTicketById(1).getTicketId());
    }

    @Test
    void getCouponById() {
    }

    @Test
    void getDestinationById() {
        assertEquals(11, new HardcodedDataAccessService().getDestinationById(11).getDestinationId());
        assertNotEquals(111111, new HardcodedDataAccessService().getDestinationById(111111).getDestinationId());
    }
}