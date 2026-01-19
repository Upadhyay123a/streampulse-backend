package com.streampulse.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.streampulse.model.AnalyticsResult;

/**
 * Thread-safe in-memory implementation of ResultStore.
 */
public class InMemoryResultStore implements ResultStore {

    private final List<AnalyticsResult> results =
            Collections.synchronizedList(new ArrayList<>());

    @Override
    public void save(AnalyticsResult result) {
        if (result != null) {
            results.add(result);
        }
    }

    @Override
    public List<AnalyticsResult> findAll() {
        return new ArrayList<>(results);
    }

    @Override
    public void clear() {
        results.clear();
    }
}
