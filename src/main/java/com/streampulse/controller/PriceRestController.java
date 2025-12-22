package com.streampulse.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.streampulse.analytics.AnalyticsUpdate;
import com.streampulse.analytics.AnalyticsService;
import com.streampulse.model.PriceUpdate;
import com.streampulse.service.PriceHistoryService;

@RestController
public class PriceRestController {

    private final PriceHistoryService historyService;
    private final AnalyticsService analyticsService;

    public PriceRestController(PriceHistoryService historyService, AnalyticsService analyticsService) {
        this.historyService = historyService;
        this.analyticsService = analyticsService;
    }

    @GetMapping("/api/prices/latest")
    public Map<String, PriceUpdate> getLatestPrices() {
        Map<String, PriceUpdate> latest = new HashMap<>();

        historyService.getAllLatest().forEach((symbol, deque) -> {
            if (!deque.isEmpty()) {
                latest.put(symbol, deque.getLast());
            }
        });

        return latest;
    }

    @GetMapping("/api/prices/history/{symbol}")
    public ArrayList<PriceUpdate> getHistory(@PathVariable String symbol) {
        return new ArrayList<>(historyService.getHistory(symbol));
    }

    @GetMapping("/api/analytics/latest")
    public Map<String, AnalyticsUpdate> getLatestAnalytics() {
        return analyticsService.getLatestAnalytics();
    }
}
