package com.streampulse.api;

import com.streampulse.model.AnalyticsResult;
import com.streampulse.model.DataPoint;

/**
 * Analytics represents a single real-time analytics unit.
 * Implementations are stateful and process streaming data.
 */
public interface Analytics {

    /**
     * Unique name of the analytics module.
     */
    String name();

    /**
     * Called for every incoming DataPoint.
     *
     * @param point incoming stream data
     * @return analytics result or null if no result produced
     */
    AnalyticsResult analyze(DataPoint point);

    /**
     * Reset internal state (optional).
     */
    default void reset() {
        // optional
    }
}
