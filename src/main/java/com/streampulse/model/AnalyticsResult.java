package com.streampulse.model;

/**
 * Represents the output of an analytics computation.
 */
public final class AnalyticsResult {

    private final String key;
    private final String type;
    private final double value;
    private final boolean flagged;
    private final long timestamp;

    public AnalyticsResult(
            String key,
            String type,
            double value,
            boolean flagged,
            long timestamp) {

        this.key = key;
        this.type = type;
        this.value = value;
        this.flagged = flagged;
        this.timestamp = timestamp;
    }

    public String getKey() {
        return key;
    }

    public String getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
/*Why:

Analytics output is standardized

Can represent any analytics

Flag allows anomaly/spike/etc. */