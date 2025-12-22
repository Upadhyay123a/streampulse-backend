package com.streampulse.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.streampulse.analytics.AnalyticsService;
import com.streampulse.model.PriceUpdate;

@Service
public class PriceGeneratorService {

    private final SimpMessagingTemplate template;
    private final AnalyticsService analyticsService;
    private final PriceHistoryService historyService;

    private final Map<String, Double> lastPrices = new HashMap<>();
    private final Random random = new Random();

    public PriceGeneratorService(
            SimpMessagingTemplate template,
            AnalyticsService analyticsService,
            PriceHistoryService historyService
    ) {
        this.template = template;
        this.analyticsService = analyticsService;
        this.historyService = historyService;

        lastPrices.put("BTC", 60000.0);
        lastPrices.put("ETH", 3500.0);
        lastPrices.put("SENSOR", 120.0);
    }

    @Scheduled(fixedRate = 1000)
    public void publishPrices() {
        long ts = System.currentTimeMillis();

        for (Map.Entry<String, Double> entry : lastPrices.entrySet()) {
            String symbol = entry.getKey();
            double prev = entry.getValue();

            double change = (random.nextDouble() - 0.5) * prev * 0.01;
            double next = Math.max(0.01, prev + change);

            lastPrices.put(symbol, next);

            PriceUpdate update = new PriceUpdate(symbol, next, ts);

            // Save to history
            historyService.add(update);

            // WebSocket push
            template.convertAndSend("/topic/prices", update);

            // Analytics processing
            analyticsService.onPrice(update);
        }
    }
}
