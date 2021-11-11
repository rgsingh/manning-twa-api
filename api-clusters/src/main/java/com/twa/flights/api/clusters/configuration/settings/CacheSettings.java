package com.twa.flights.api.clusters.configuration.settings;

public class CacheSettings {

    int maxElements;
    int duration;

    public int getMaxElements() {
        return maxElements;
    }

    public void setMaxElements(int maxElements) {
        this.maxElements = maxElements;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
