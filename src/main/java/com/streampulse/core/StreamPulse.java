package com.streampulse.core;

import java.util.Objects;

import com.streampulse.api.Analytics;
import com.streampulse.api.ResultListener;
import com.streampulse.api.StreamEngine;
import com.streampulse.model.AnalyticsResult;
import com.streampulse.model.DataPoint;
import com.streampulse.store.ResultStore;

/**
 * Core library entry point for StreamPulse.
 *
 * Framework-agnostic.
 * Owns analytics execution + result persistence.
 */
public class StreamPulse implements ResultListener {

    private final StreamEngine engine;
    private final ResultStore resultStore;

    public StreamPulse(StreamEngine engine, ResultStore resultStore) {
        this.engine = Objects.requireNonNull(engine);
        this.resultStore = Objects.requireNonNull(resultStore);
        this.engine.addResultListener(this);
    }

    public void registerAnalytics(Analytics analytics) {
        engine.register(analytics);
    }

    public void ingest(DataPoint point) {
        engine.ingest(point);
    }

    public void reset() {
        engine.reset();
        resultStore.clear();
    }

    @Override
    public void onResult(AnalyticsResult result) {
        resultStore.save(result);
    }
}
