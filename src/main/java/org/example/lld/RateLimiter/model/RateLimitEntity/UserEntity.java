package org.example.lld.RateLimiter.model.RateLimitEntity;

import lombok.AllArgsConstructor;
import org.example.lld.RateLimiter.model.RateLimitConfig;
import org.example.lld.RateLimiter.strategy.ConfigProvider.IRateLimitConfigProvider;

@AllArgsConstructor
public class UserEntity implements RateLimitEntity {
    private String userId;
    private IRateLimitConfigProvider provider;

    @Override
    public String getId() {
        return "User:" + userId;
    }

    @Override
    public RateLimitConfig getConfig() {
        return provider.getRateLimitConfig();
    }
}
