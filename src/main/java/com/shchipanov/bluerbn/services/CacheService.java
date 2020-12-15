package com.shchipanov.bluerbn.services;

import com.shchipanov.bluerbn.dto.BaggageCheckInResultDTO;
import com.shchipanov.bluerbn.dto.CouponValidationDTO;
import com.shchipanov.bluerbn.services.caches.FlightApplicationCacheEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Log4j2
@Service
public class CacheService {

    ConcurrentHashMap<Integer, FlightApplicationCacheEntity<Boolean>> ticketsCache;
    ConcurrentHashMap<Integer, FlightApplicationCacheEntity<CouponValidationDTO>> couponsCache;
    ConcurrentHashMap<String, FlightApplicationCacheEntity<BaggageCheckInResultDTO>> baggageCache;

    public void addToTicketCache(int ticketId, boolean isValid) {
        ticketsCache.put(ticketId, new FlightApplicationCacheEntity<>(isValid, System.currentTimeMillis()));
    }

    public void addToBaggageCache(int destinationId, String baggageId, boolean checkedIn) {
        baggageCache.put(baggageId, new FlightApplicationCacheEntity<>(
                new BaggageCheckInResultDTO(destinationId, baggageId, checkedIn), System.currentTimeMillis()));
    }

    public void addToCouponsCache(int couponId, CouponValidationDTO result) {
        couponsCache.put(couponId, new FlightApplicationCacheEntity<>(result,  System.currentTimeMillis()));
    }

    public boolean getTicketValidationCache(int ticketId) {
        return ticketsCache.get(ticketId).getData();
    }

    public CouponValidationDTO getCouponValidationResultFromCache(int couponId) {
        return couponsCache.get(couponId).getData();
    }

    public BaggageCheckInResultDTO getBaggageCheckInCache(String baggage) {
        return baggageCache.get(baggage).getData();
    }

    public boolean containsTicketValidationCache(int ticketId) {
        return ticketsCache.containsKey(ticketId);
    }

    public boolean containsBaggageCheckInCache(int destinationId, String baggage) {
        return baggageCache.entrySet().stream().anyMatch(entity -> entity.getKey().equals(baggage)
                && entity.getValue().getData().getDestinationId() == destinationId);
    }

    public boolean containsCouponCache(int couponId) {
        return couponsCache.containsKey(couponId);
    }

    @Scheduled(fixedRate = 5000)
    public void cleanUpCache() {
        log.debug("Cache cleanup");
        ticketsCache.entrySet().removeAll(
                ticketsCache.entrySet().stream().filter(entity -> entity.getValue().expired()).collect(Collectors.toList()));
    }

    @PostConstruct
    public void initCacheMaps() {
        ticketsCache = new ConcurrentHashMap<>();
        couponsCache = new ConcurrentHashMap<>();
        baggageCache = new ConcurrentHashMap<>();
    }
}
