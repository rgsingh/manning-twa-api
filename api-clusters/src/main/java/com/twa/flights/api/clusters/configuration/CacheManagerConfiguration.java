package com.twa.flights.api.clusters.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.twa.flights.api.clusters.enums.CacheName;
import com.twa.flights.api.clusters.service.CatalogService;
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

    private CatalogService catalogService;

    private CacheConfiguration cacheConfiguration;

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCacheNames(Arrays.asList(CacheName.CITY_CACHE_NAME.name()));
//        caffeineCacheManager.setCacheLoader(new CacheLoader<Object, Object>() {
//            @Override
//            public @Nullable Object load(@NonNull Object o) throws Exception {
//                CityDTO cityDTO = catalogService.getCity((String)o);
//                if (cityDTO != null) {
//                    return cityDTO;
//                } else {
//                    throw new RuntimeException("City not found");
//                }
//            }
//        });
        caffeineCacheManager.setCaffeine(caffeineConfig(cacheConfiguration));

        return caffeineCacheManager;
    }

    @Bean
    @Autowired
    public Caffeine caffeineConfig(CacheConfiguration cacheConfiguration) {
        return Caffeine.newBuilder()
                .expireAfterWrite(cacheConfiguration.getCacheSettings().getDuration(), TimeUnit.MINUTES)
                .maximumSize(cacheConfiguration.getCacheSettings().getMaxElements());
    }
}
