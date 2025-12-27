package com.streampulse.store;

import java.util.List;

import com.streampulse.model.AnalyticsResult;

public interface ResultStore {

    void add(AnalyticsResult result);

    List<AnalyticsResult> getAll();

    void clear();
}
