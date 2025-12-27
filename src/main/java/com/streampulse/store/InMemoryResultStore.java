package com.streampulse.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.streampulse.model.AnalyticsResult;

public class InMemoryResultStore implements ResultStore {

    private final List<AnalyticsResult> results = new ArrayList<>();

    @Override
    public synchronized void add(AnalyticsResult result) {
        results.add(result);
    }

    @Override
    public synchronized List<AnalyticsResult> getAll() {
        return Collections.unmodifiableList(results);
    }

    @Override
    public synchronized void clear() {
        results.clear();
    }
}
