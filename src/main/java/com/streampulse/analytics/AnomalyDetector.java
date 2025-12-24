package com.streampulse.analytics;

import java.util.ArrayDeque;
import java.util.Deque;

import com.streampulse.api.Analytics;
import com.streampulse.model.AnalyticsResult;
import com.streampulse.model.DataPoint;

/**
 * AnomalyDetector detects statistical anomalies
 * using Z-Score over a sliding window.
 */
public class AnomalyDetector implements Analytics {

    private final int windowSize;
    private final double zThreshold;
    private final Deque<Double> window = new ArrayDeque<>();

    public AnomalyDetector(int windowSize, double zThreshold) {
        if (windowSize <= 1) {
            throw new IllegalArgumentException("Window size must be > 1");
        }
        if (zThreshold <= 0) {
            throw new IllegalArgumentException("Z-threshold must be > 0");
        }
        this.windowSize = windowSize;
        this.zThreshold = zThreshold;
    }

    @Override
    public String name() {
        return "ANOMALY_DETECTOR";
    }

    @Override
    public AnalyticsResult analyze(DataPoint point) {
        double value = point.getValue();

        window.addLast(value);
        if (window.size() > windowSize) {
            window.removeFirst();
        }

        if (window.size() < windowSize) {
            return null; // not enough data
        }

        double mean = computeMean();
        double std = computeStdDev(mean);

        if (std == 0.0) {
            return null;
        }

        double zScore = Math.abs((value - mean) / std);

        if (zScore >= zThreshold) {
            return new AnalyticsResult(
                    point.getKey(),
                    name(),
                    value,
                    true,
                    point.getTimestamp()
            );
        }

        return null;
    }

    @Override
    public void reset() {
        window.clear();
    }

    private double computeMean() {
        double sum = 0.0;
        for (double v : window) {
            sum += v;
        }
        return sum / window.size();
    }

    private double computeStdDev(double mean) {
        double sum = 0.0;
        for (double v : window) {
            double diff = v - mean;
            sum += diff * diff;
        }
        return Math.sqrt(sum / (window.size() - 1));
    }
}
/*
✅ Real Statistical Detection

Z-Score is widely used in:

Finance

IoT

Monitoring systems

Fraud detection

✅ Sliding Window

Responds to recent behavior, not stale history

✅ Noise Reduction

No output until window is full

Avoids division-by-zero

✅ Generic & Reusable

No domain assumptions

Works with any stream */