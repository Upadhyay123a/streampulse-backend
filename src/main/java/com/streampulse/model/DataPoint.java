package com.streampulse.model;

/**
 * Represents a single event in a real-time data stream.
 * This class is domain-agnostic and can represent
 * prices, sensor values, metrics, logs, etc.
 */
public final class DataPoint {

    private final String key;
    private final double value;
    private final long timestamp;

    public DataPoint(String key, double value, long timestamp) {
        this.key = key;
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getKey() {
        return key;
    }

    public double getValue() {
        return value;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
