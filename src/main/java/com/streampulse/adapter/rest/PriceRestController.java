package com.streampulse.adapter.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.streampulse.model.AnalyticsResult;
import com.streampulse.store.ResultStore;

/**
 * REST adapter for exposing analytics results.
 *
 * This controller does NOT perform analytics.
 * It only reads from the ResultStore.
 */
@RestController
@RequestMapping("/api/analytics")
public class PriceRestController {

    private final ResultStore resultStore;

    public PriceRestController(ResultStore resultStore) {
        this.resultStore = resultStore;
    }

    /**
     * Fetch all analytics results.
     */
    @GetMapping
    public List<AnalyticsResult> getAllResults() {
        return resultStore.getAll();
    }
}
