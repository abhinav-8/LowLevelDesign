package org.example.lld.RateLimiter.driver;

import lombok.SneakyThrows;
import org.example.lld.RateLimiter.model.RateLimitEntity.ApiEntity;
import org.example.lld.RateLimiter.model.RateLimitEntity.RateLimitEntity;
import org.example.lld.RateLimiter.model.RateLimitEntity.UserEntity;
import org.example.lld.RateLimiter.service.RateLimiter;
import org.example.lld.RateLimiter.strategy.ConfigProvider.ConstantConfigProvider;
import org.example.lld.RateLimiter.strategy.ConfigProvider.IRateLimitConfigProvider;
import org.example.lld.RateLimiter.strategy.RateLimitingStrategy.RateLimitingStrategy;
import org.example.lld.RateLimiter.strategy.RateLimitingStrategy.TokenBucket;

import java.util.List;

public class RateLimiterApplication {
    @SneakyThrows
    static void main(String[] args) {
        RateLimitingStrategy strategy = new TokenBucket();
        RateLimitingStrategy strategy2 = new TokenBucket();
        RateLimiter rateLimiter = new RateLimiter(strategy);

       IRateLimitConfigProvider constantConfigProvider = new ConstantConfigProvider(10, 3, 10000);
       List<RateLimitEntity> entities = List.of(
                new ApiEntity("/pay", constantConfigProvider),
                new UserEntity("user1", constantConfigProvider)
       );

       for(int i = 0 ; i < 40 ; i++) {
           boolean allowed = rateLimiter.allow(entities);
           if(!allowed) {
               System.out.println("Rate limit exceeded!" + i + "\n");
               Thread.sleep(10000);
           } else {
               System.out.println("Request allowed!" + i + "\n");
           }

       }
    }
}
