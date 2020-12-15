package com.shchipanov.bluerbn.services;

import com.shchipanov.bluerbn.dao.Destination;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
public class BaggageCheckInService {

    @Autowired
    CacheService cacheService;

    @Autowired
    DataAccessService dataAccessService;

    private Map<String, Integer> checkedInBaggage;

    public boolean checkInBaggage(String baggageID, int destinationId) {
        log.debug(String.format("trying to checkIn baggage: %s to destination %d", baggageID, destinationId));

        if (cacheService.containsBaggageCheckInCache(destinationId, baggageID)) {
            return cacheService.getBaggageCheckInCache(baggageID).isCheckedIn();
        }

        Destination existedDestination = dataAccessService.getDestinationById(destinationId);

        if (existedDestination.getDestinationId() == destinationId) {
            cacheService.addToBaggageCache(destinationId, baggageID, true);
            checkedInBaggage.put(baggageID, destinationId);
            return true;
        } else {
            cacheService.addToBaggageCache(destinationId, baggageID, false);
            return false;
        }
    }

    @PostConstruct
    public void initCheckedInBaggageStore() {
        this.checkedInBaggage = new HashMap<>();
    }
}
