package com.shchipanov.bluerbn.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shchipanov.bluerbn.dao.Coupon;
import com.shchipanov.bluerbn.dao.Destination;
import com.shchipanov.bluerbn.dao.Ticket;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;

@Log4j2
@Service
public class HardcodedDataAccessService implements DataAccessService {

    public Ticket getTicketById(int ticketId) {
        log.debug("Search ticket by id: " + ticketId);
        try {
            URL ticketsFilePath = new ClassPathResource("dataSource/tickets.json", this.getClass().getClassLoader()).getURL();
            List<Ticket> tickets = new ObjectMapper().readValue(ticketsFilePath, new TypeReference<>(){});
            log.debug("All tickets count: " + tickets.size());
            return tickets.stream().filter(ticket -> ticket.getTicketId() == ticketId).findFirst().orElse(new Ticket());
        } catch (Exception exception) {
            log.error("Something went wrong", exception);
            throw new IllegalStateException("Error accessing dataSource", exception);
        }
    }

    public Coupon getCouponById(int couponId) {

        return new Coupon();
    }

    public Destination getDestinationById(int destinationId) {
        log.debug("Search destination by id: " + destinationId);
        try {
            URL destinationsFilePath = new ClassPathResource("dataSource/destinations.json", this.getClass().getClassLoader()).getURL();
            List<Destination> destinations = new ObjectMapper().readValue(destinationsFilePath, new TypeReference<>(){});
            log.debug("All tickets count: " + destinations.size());
            return destinations.stream().filter(ticket -> ticket.getDestinationId() == destinationId)
                    .findFirst().orElse(new Destination());
        } catch (Exception exception) {
            log.error("Something went wrong", exception);
            throw new IllegalStateException("Error accessing dataSource", exception);
        }
    }
}
