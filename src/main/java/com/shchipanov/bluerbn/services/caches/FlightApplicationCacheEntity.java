package com.shchipanov.bluerbn.services.caches;

import org.springframework.beans.factory.annotation.Value;

public class FlightApplicationCacheEntity<T> {
    private final T data;
    private long lastAccessTimeStamp;

    @Value("flight.app.expiration.delay")
    private long expirationDelay;

    public boolean expired() {
        return System.currentTimeMillis() - lastAccessTimeStamp > expirationDelay;
    }


    public FlightApplicationCacheEntity(T data, long lastAccessTimeStamp) {
        this.data = data;
        this.lastAccessTimeStamp = lastAccessTimeStamp;
    }

    public T getData() {
        this.lastAccessTimeStamp = System.currentTimeMillis();
        return data;
    }
}
