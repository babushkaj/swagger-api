package com.katsiankou.swagger.configuration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.katsiankou.swagger.configuration.properties.CacheProperties;
import com.katsiankou.swagger.configuration.properties.CacheProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager(CacheProperties cacheProperties) {
        var caches = cacheProperties.config().stream()
            .map(props -> new CaffeineCache(props.name(), buildCacheSpec(props)))
            .toList();

        var simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(caches);
        return simpleCacheManager;
    }

    private Cache<Object, Object> buildCacheSpec(CacheProperty cacheProperty) {
        return Caffeine.newBuilder()
            .expireAfterWrite(cacheProperty.expireAfterWriteHours(), TimeUnit.HOURS)
            .maximumSize(cacheProperty.maximumSize())
            .build();
    }
}
