package com.shchipanov.bluerbn.services;

import com.shchipanov.bluerbn.dao.Ticket;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class TicketCheckService {

    @Autowired
    CacheService cacheService;

    @Autowired
    DataAccessService dataAccessService;

    public boolean checkTicketAvailable(int ticketId) {
        log.debug(String.format("check ticket id: %d", ticketId));
        if (cacheService.containsTicketValidationCache(ticketId)) {
            return cacheService.getTicketValidationCache(ticketId);
        }
        Ticket ticketFromDataAccessService = dataAccessService.getTicketById(ticketId);
        log.debug("Ticket from data source:" + ticketFromDataAccessService);

        if (ticketFromDataAccessService.getTicketId() != ticketId) {
            log.debug(String.format("Ticket wasn't found id %d is invalid", ticketId));
            cacheService.addToTicketCache(ticketId, false);
            return false;
        } else {
            log.debug(String.format("Ticket was found id %d is valid", ticketId));
            cacheService.addToTicketCache(ticketId, true);
            return true;
        }
    }
}
