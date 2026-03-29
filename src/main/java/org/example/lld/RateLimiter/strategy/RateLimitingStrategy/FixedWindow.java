package org.example.lld.RateLimiter.strategy.RateLimitingStrategy;

import lombok.AllArgsConstructor;
import org.example.lld.RateLimiter.model.RateLimitConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FixedWindow implements RateLimitingStrategy {

    @AllArgsConstructor
    static class Window {
        long windowStart;
        int count;
    }

    private final Map<String, Window> windows = new ConcurrentHashMap<>();

    @Override
    public boolean allowRequest(String key, RateLimitConfig rateLimitConfig) {
        long now =  System.currentTimeMillis();
        long currentWindowStart = (now / rateLimitConfig.getWindowSizeMillis()) * rateLimitConfig.getWindowSizeMillis();

        windows.putIfAbsent(key, new Window(currentWindowStart, 0));
        Window window = windows.get(key);

        if(window.windowStart != currentWindowStart) {
            window.windowStart = currentWindowStart;
            window.count = 0;
        }

        if(window.count < rateLimitConfig.getWindowSizeMillis()) {
            window.count++;
            return true;
        }

        return false;
    }
}
