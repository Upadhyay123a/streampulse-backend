package com.streampulse.core;

import java.util.Objects;

import com.streampulse.api.Analytics;
import com.streampulse.api.ResultListener;
import com.streampulse.api.StreamEngine;
import com.streampulse.engine.DefaultStreamEngine;
import com.streampulse.model.DataPoint;

/**
 * Core library entry point for StreamPulse.
 *
 * This class is framework-agnostic and represents
 * the pure StreamPulse analytics engine.
 */
public class StreamPulse {

    private final StreamEngine engine;

    public StreamPulse() {
        this.engine = new DefaultStreamEngine();
    }

    public StreamPulse(StreamEngine engine) {
        this.engine = Objects.requireNonNull(engine);
    }

    public void registerAnalytics(Analytics analytics) {
        engine.register(analytics);
    }

    public void addResultListener(ResultListener listener) {
        engine.addResultListener(listener);
    }

    public void ingest(DataPoint point) {
        engine.ingest(point);
    }

    public void reset() {
        engine.reset();
    }
}
