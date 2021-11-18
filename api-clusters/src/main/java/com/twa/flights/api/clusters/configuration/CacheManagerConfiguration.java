package com.twa.flights.api.clusters.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Lists;
import com.twa.flights.api.clusters.configuration.settings.CacheSettings;
import com.twa.flights.api.clusters.connector.CatalogConnector;
import com.twa.flights.api.clusters.enums.CacheName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Configuration
@EnableCaching
public class CacheManagerConfiguration {

    @Autowired
    private CacheConfiguration cacheConfiguration;

    @Autowired
    private CatalogConnector catalogConnector;

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager catalogCacheManager = new SimpleCacheManager();
        catalogCacheManager.setCaches(Lists.newArrayList(caffeineCache(CacheName.Constants.CITY_CACHE_VALUE,
                cacheConfiguration.getCache(), catalogConnector::getCityByCode)));

        return catalogCacheManager;
    }

    @Bean
    public Caffeine caffeineConfig() {
        return Caffeine.newBuilder().expireAfterWrite(cacheConfiguration.getCache().getDuration(), TimeUnit.MINUTES)
                .maximumSize(cacheConfiguration.getCache().getMaxElements());
    }

    public static CaffeineCache caffeineCache(String cacheName, CacheSettings settings,
            Function<String, Object> serviceCall) {

        return new CaffeineCache(cacheName,
                Caffeine.newBuilder().expireAfterWrite(settings.getDuration(), TimeUnit.MINUTES)
                        .maximumSize(settings.getMaxElements()).build(key -> serviceCall.apply(key.toString())));
    }

}
