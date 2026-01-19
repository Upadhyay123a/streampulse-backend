package com.streampulse.store;

import java.util.List;

import com.streampulse.model.AnalyticsResult;

/**
 * Storage abstraction for analytics results.
 */
public interface ResultStore {

    void save(AnalyticsResult result);

    List<AnalyticsResult> findAll();

    void clear();
}
