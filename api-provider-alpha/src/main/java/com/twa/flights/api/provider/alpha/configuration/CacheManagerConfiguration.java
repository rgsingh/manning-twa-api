package com.twa.flights.api.provider.alpha.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.twa.flights.api.provider.alpha.enums.CacheName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheManagerConfiguration {

    @Autowired
    private CacheConfiguration cacheConfiguration;

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager catalogCacheManager = new CaffeineCacheManager();
        catalogCacheManager.setCacheNames(Arrays.asList(CacheName.Constants.CITY_CACHE_VALUE));
        catalogCacheManager.setCaffeine(caffeineConfig());

        return catalogCacheManager;
    }

    @Bean
    public Caffeine caffeineConfig() {
        return Caffeine.newBuilder().expireAfterWrite(cacheConfiguration.getCache().getDuration(), TimeUnit.MINUTES)
                .maximumSize(cacheConfiguration.getCache().getMaxElements());
    }
}
