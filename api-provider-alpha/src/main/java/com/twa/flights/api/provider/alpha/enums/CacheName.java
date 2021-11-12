package com.twa.flights.api.provider.alpha.enums;

public enum CacheName {
    CITY_CACHE_NAME(Constants.CITY_CACHE_VALUE);

    CacheName(String cacheNameString) {
    }

    public static class Constants {
        public static final String CITY_CACHE_VALUE = "cities";
    }
}
