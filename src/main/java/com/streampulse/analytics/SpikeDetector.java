package com.streampulse.analytics;

import com.streampulse.api.Analytics;
import com.streampulse.model.AnalyticsResult;
import com.streampulse.model.DataPoint;

/**
 * SpikeDetector detects sudden percentage changes
 * between consecutive data points.
 *
 * Example:
 * previous = 100
 * current  = 110
 * threshold = 0.05 (5%)
 * spike detected
 */
public class SpikeDetector implements Analytics {

    private final double threshold;
    private Double lastValue = null;

    /**
     * @param threshold percentage threshold (e.g. 0.05 = 5%)
     */
    public SpikeDetector(double threshold) {
        if (threshold <= 0) {
            throw new IllegalArgumentException("Threshold must be > 0");
        }
        this.threshold = threshold;
    }

    @Override
    public String name() {
        return "SPIKE_DETECTOR";
    }

    @Override
    public AnalyticsResult analyze(DataPoint point) {
        double current = point.getValue();

        if (lastValue == null) {
            lastValue = current;
            return null;
        }

        double change = Math.abs(current - lastValue) / lastValue;
        lastValue = current;

        if (change >= threshold) {
            return new AnalyticsResult(
                    point.getKey(),
                    name(),
                    current,
                    true,
                    point.getTimestamp()
            );
        }

        return null;
    }

    @Override
    public void reset() {
        lastValue = null;
    }
}
