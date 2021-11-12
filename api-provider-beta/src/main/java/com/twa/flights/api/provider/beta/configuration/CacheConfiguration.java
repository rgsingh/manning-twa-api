package com.twa.flights.api.provider.beta.configuration;

import com.twa.flights.api.provider.beta.configuration.settings.CacheSettings;
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
