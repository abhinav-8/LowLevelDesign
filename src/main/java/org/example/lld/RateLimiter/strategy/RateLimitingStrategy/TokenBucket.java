package org.example.lld.RateLimiter.strategy.RateLimitingStrategy;

import lombok.AllArgsConstructor;
import org.example.lld.RateLimiter.model.RateLimitConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucket implements RateLimitingStrategy {

    @AllArgsConstructor
    static class Bucket {
        long lastRefillTime;
        int tokens;
    }

    private final Map<String, Bucket> tokenBucketMap = new ConcurrentHashMap<>();

    @Override
    public boolean allowRequest(String key, RateLimitConfig rateLimitConfig) {
        tokenBucketMap.putIfAbsent(key, new Bucket(System.currentTimeMillis(), rateLimitConfig.getCapacity()));
        Bucket bucket = tokenBucketMap.get(key);

        refill(key, rateLimitConfig);

        if(bucket.tokens > 0) {
            bucket.tokens--;
            return true;
        }

        return false;
    }

    private void refill(String key, RateLimitConfig rateLimitConfig) {
        Bucket bucket = tokenBucketMap.get(key);
        long now = System.currentTimeMillis();
        long elapsed = now - bucket.lastRefillTime;
        long intervals = elapsed/ rateLimitConfig.getWindowSizeMillis();

        if(intervals > 0) {
            int tokensToAdd = (int) intervals * rateLimitConfig.getRefillRate();
            bucket.tokens = Math.min(rateLimitConfig.getCapacity(), bucket.tokens + tokensToAdd);
            bucket.lastRefillTime += intervals * rateLimitConfig.getWindowSizeMillis();
        }
    }
}
