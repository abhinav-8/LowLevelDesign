package org.example.lld.RateLimiter.strategy.RateLimitingStrategy;

import org.example.lld.RateLimiter.model.RateLimitConfig;

public interface RateLimitingStrategy {
    boolean allowRequest(String key, RateLimitConfig rateLimitConfig);
}
