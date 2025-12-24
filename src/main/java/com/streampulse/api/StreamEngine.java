package com.streampulse.api;

import com.streampulse.model.DataPoint;

/**
 * Central engine responsible for coordinating
 * data flow, analytics execution, and result dispatch.
 */
public interface StreamEngine {

    /**
     * Register an analytics module.
     */
    void register(Analytics analytics);

    /**
     * Register a result listener.
     */
    void addResultListener(ResultListener listener);

    /**
     * Accept incoming data into the stream.
     */
    void ingest(DataPoint point);

    /**
     * Clear all internal state.
     */
    void reset();
}
/*


Clear responsibilities

Extensible

Framework-independent

Matches real stream engines conceptually */