package org.example.lld.RateLimiter.model.RateLimitEntity;

import org.example.lld.RateLimiter.model.RateLimitConfig;

public interface RateLimitEntity {
    String getId();
    RateLimitConfig getConfig();
}
