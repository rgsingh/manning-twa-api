package com.twa.flights.api.provider.alpha.configuration;

import com.twa.flights.api.provider.alpha.configuration.settings.CacheSettings;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class CacheConfiguration {

    private CacheSettings cache;

    public CacheSettings getCache() {
        return cache;
    }

    public void setCache(CacheSettings cache) {
        this.cache = cache;
    }
}
