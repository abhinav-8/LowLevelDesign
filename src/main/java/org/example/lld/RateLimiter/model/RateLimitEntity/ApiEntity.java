package org.example.lld.RateLimiter.model.RateLimitEntity;

import lombok.AllArgsConstructor;
import org.example.lld.RateLimiter.model.RateLimitConfig;
import org.example.lld.RateLimiter.strategy.ConfigProvider.IRateLimitConfigProvider;

@AllArgsConstructor
public class ApiEntity implements RateLimitEntity {
    private String apiPath;
    private IRateLimitConfigProvider provider;

    @Override
    public String getId() {
        return "API:" + apiPath;
    }

    @Override
    public RateLimitConfig getConfig() {
        return provider.getRateLimitConfig();
    }
}
