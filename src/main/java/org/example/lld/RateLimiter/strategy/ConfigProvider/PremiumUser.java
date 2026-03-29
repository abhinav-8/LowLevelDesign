package org.example.lld.RateLimiter.strategy.ConfigProvider;

import org.example.lld.RateLimiter.model.RateLimitConfig;

public class PremiumUser implements IRateLimitConfigProvider {
    private final RateLimitConfig config;

    public PremiumUser() {
        this.config = new RateLimitConfig(10, 100, 1000);
    }
    @Override
    public RateLimitConfig getRateLimitConfig() {
        return config;
    }
}
