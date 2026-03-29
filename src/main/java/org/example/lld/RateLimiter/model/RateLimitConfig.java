package org.example.lld.RateLimiter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RateLimitConfig {
    private int capacity;
    private int refillRate;
    private long windowSizeMillis;
}
