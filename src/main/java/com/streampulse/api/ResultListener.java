package com.streampulse.api;

import com.streampulse.model.AnalyticsResult;

/**
 * Receives analytics results from the stream engine.
 */
@FunctionalInterface
public interface ResultListener {

    /**
     * Called whenever an analytics result is produced.
     */
    void onResult(AnalyticsResult result);
}
