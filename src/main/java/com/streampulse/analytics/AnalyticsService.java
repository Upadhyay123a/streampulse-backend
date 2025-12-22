package com.streampulse.analytics;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.streampulse.model.PriceUpdate;

@Service
public class AnalyticsService {

    private static final int WINDOW_SIZE = 30;              // sliding window size
    private static final double ZSCORE_THRESHOLD = 3.0;     // anomaly threshold
    private static final double SPIKE_PERCENT_THRESHOLD = 0.05; // 5% change

    private final SimpMessagingTemplate messagingTemplate;

    // sliding windows per symbol
    private final Map<String, Deque<Double>> windows = new HashMap<>();

    // last price per symbol
    private final Map<String, Double> lastPrice = new HashMap<>();

    // store latest analytics result
    private final Map<String, AnalyticsUpdate> latestAnalytics = new HashMap<>();

    public AnalyticsService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Called for each price update
    public void onPrice(PriceUpdate update) {
        String symbol = update.getSymbol();
        double price = update.getPrice();
        long ts = update.getTimestamp();

        windows.putIfAbsent(symbol, new ArrayDeque<>());
        Deque<Double> window = windows.get(symbol);

        // Maintain sliding window
        window.addLast(price);
        if (window.size() > WINDOW_SIZE) {
            window.removeFirst();
        }

        // Compute stats
        double mean = computeMean(window);
        double std = computeStdDev(window, mean);
        double z = (std == 0.0) ? 0.0 : (price - mean) / std;

        boolean anomaly = Math.abs(z) >= ZSCORE_THRESHOLD;

        // Spike detection
        Double previous = lastPrice.get(symbol);
        boolean spike = false;

        if (previous != null && previous > 0) {
            double pctChange = Math.abs(price - previous) / previous;
            spike = pctChange >= SPIKE_PERCENT_THRESHOLD;
        }

        lastPrice.put(symbol, price);

        // Build analytics result
        AnalyticsUpdate a = new AnalyticsUpdate(
                symbol, price, mean, std, z, anomaly, spike, ts
        );

        // store latest analytics for REST endpoint
        latestAnalytics.put(symbol, a);

        // publish via WebSocket
        messagingTemplate.convertAndSend("/topic/analytics", a);
    }

    // REST endpoint will call this
    public Map<String, AnalyticsUpdate> getLatestAnalytics() {
        return latestAnalytics;
    }

    private double computeMean(Deque<Double> window) {
        if (window.isEmpty()) return 0.0;

        double sum = 0;
        for (double v : window) sum += v;

        return sum / window.size();
    }

    private double computeStdDev(Deque<Double> window, double mean) {
        if (window.size() <= 1) return 0.0;

        double sum = 0;
        for (double v : window) {
            sum += (v - mean) * (v - mean);
        }

        return Math.sqrt(sum / (window.size() - 1));
    }
}
