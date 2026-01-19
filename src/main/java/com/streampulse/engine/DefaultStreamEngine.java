package com.streampulse.engine;

import java.util.ArrayList;
import java.util.List;

import com.streampulse.api.Analytics;
import com.streampulse.api.ResultListener;
import com.streampulse.api.StreamEngine;
import com.streampulse.model.AnalyticsResult;
import com.streampulse.model.DataPoint;

public class DefaultStreamEngine implements StreamEngine {

    private final List<Analytics> analyticsList = new ArrayList<>();
    private final List<ResultListener> listeners = new ArrayList<>();

    @Override
    public void register(Analytics analytics) {
        if (analytics == null) {
            throw new IllegalArgumentException("Analytics cannot be null");
        }
        analyticsList.add(analytics);
    }

    @Override
    public void addResultListener(ResultListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("ResultListener cannot be null");
        }
        listeners.add(listener);
    }

    @Override
    public void ingest(DataPoint point) {
        if (point == null) {
            return;
        }

        for (Analytics analytics : analyticsList) {
            try {
                AnalyticsResult result = analytics.analyze(point);
                if (result != null) {
                    publish(result);
                }
            } catch (Exception e) {
                // isolate analytics failure (production-grade)
                System.err.println(
                    "Analytics failed: " + analytics.name() + " -> " + e.getMessage()
                );
            }
        }
    }

    private void publish(AnalyticsResult result) {
        for (ResultListener listener : listeners) {
            listener.onResult(result);
        }
    }

    @Override
    public void reset() {
        for (Analytics analytics : analyticsList) {
            analytics.reset();
        }
    }
}
