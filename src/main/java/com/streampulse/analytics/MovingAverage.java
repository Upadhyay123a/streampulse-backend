package com.streampulse.analytics;

import java.util.ArrayDeque;
import java.util.Deque;

import com.streampulse.api.Analytics;
import com.streampulse.model.AnalyticsResult;
import com.streampulse.model.DataPoint;

public class MovingAverage implements Analytics {

    private final int windowSize;
    private final Deque<Double> window = new ArrayDeque<>();
    private double sum = 0.0;

    public MovingAverage(int windowSize) {
        if (windowSize <= 0) {
            throw new IllegalArgumentException("Window size must be > 0");
        }
        this.windowSize = windowSize;
    }

    @Override
    public String name() {
        return "MOVING_AVERAGE";
    }

    @Override
    public AnalyticsResult analyze(DataPoint point) {
        double value = point.getValue();

        window.addLast(value);
        sum += value;

        if (window.size() > windowSize) {
            sum -= window.removeFirst();
        }

        if (window.size() < windowSize) {
            return null;
        }

        double average = sum / windowSize;

        return new AnalyticsResult(
                point.getKey(),     // key
                name(),              // type
                average,             // value
                false,               // flagged
                point.getTimestamp() // timestamp
        );
    }

    @Override
    public void reset() {
        window.clear();
        sum = 0.0;
    }
}
/*

✅ Analytics.name()

Enables engine-level identification

Used by logging, routing, storage, UI

✅ DataPoint.getKey()

Domain-agnostic replacement for symbol

Works for prices, sensors, metrics, logs

✅ AnalyticsResult consistency

key + type + value + flagged + timestamp

Uniform output for all analytics

🟢 STATUS CHECK
Component	Status
StreamEngine	✅ Correct
Analytics API	✅ Correct
DataPoint model	✅ Correct
MovingAverage	✅ FIXED
Compiler errors	❌ RESOLVED */