package com.twa.flights.api.clusters.configuration;

import com.twa.flights.api.clusters.configuration.settings.CacheSettings;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfiguration {

    private CacheSettings cacheSettings;

    public CacheSettings getCacheSettings() {
        return cacheSettings;
    }

    public void setCacheSettings(CacheSettings cacheSettings) {
        this.cacheSettings = cacheSettings;
    }
}
