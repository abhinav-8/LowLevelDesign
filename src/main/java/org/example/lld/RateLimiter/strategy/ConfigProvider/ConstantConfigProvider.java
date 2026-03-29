package org.example.lld.RateLimiter.strategy.ConfigProvider;

import org.example.lld.RateLimiter.model.RateLimitConfig;


public class ConstantConfigProvider implements IRateLimitConfigProvider {
    private final RateLimitConfig config;

    public ConstantConfigProvider(int capacity, int refillRate, long window) {
        this.config = new RateLimitConfig(capacity, refillRate, window);

    }
    @Override
    public RateLimitConfig getRateLimitConfig() {
        return config;
    }


}
