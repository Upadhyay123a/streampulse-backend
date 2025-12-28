package com.streampulse.engine;

import java.util.ArrayList;
import java.util.List;

import com.streampulse.api.Analytics;
import com.streampulse.api.ResultListener;
import com.streampulse.api.StreamEngine;
import com.streampulse.model.AnalyticsResult;
import com.streampulse.model.DataPoint;

/**
 * Default in-memory implementation of StreamEngine.
 *
 * Responsibilities:
 *  - Accept incoming DataPoints
 *  - Forward them to registered Analytics modules
 *  - Collect AnalyticsResults
 *  - Publish results to registered ResultListeners
 *
 * This class is framework-agnostic and library-safe.
 */
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
            AnalyticsResult result = analytics.analyze(point);

            if (result != null) {
                publish(result);
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
