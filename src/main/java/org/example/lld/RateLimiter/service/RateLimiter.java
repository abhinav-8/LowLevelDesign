package org.example.lld.RateLimiter.service;

import org.example.lld.RateLimiter.model.RateLimitEntity.RateLimitEntity;
import org.example.lld.RateLimiter.strategy.RateLimitingStrategy.RateLimitingStrategy;

import java.util.List;

public class RateLimiter {
    private final RateLimitingStrategy rateLimitingStrategy;

    public RateLimiter(RateLimitingStrategy rateLimitingStrategy) {
        this.rateLimitingStrategy = rateLimitingStrategy;
    }

    public boolean allow(List<RateLimitEntity> entities) {
        for (RateLimitEntity entity : entities) {
            boolean allowed = rateLimitingStrategy.allowRequest(entity.getId(), entity.getConfig());
            if(!allowed) {
                return false;
            }
        }
        return true;
    }
}
