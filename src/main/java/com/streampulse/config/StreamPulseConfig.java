package com.streampulse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.streampulse.adapter.websocket.WebSocketPublisher;
import com.streampulse.analytics.AnomalyDetector;
import com.streampulse.analytics.MovingAverage;
import com.streampulse.analytics.SpikeDetector;
import com.streampulse.core.StreamPulse;
import com.streampulse.engine.DefaultStreamEngine;
import com.streampulse.store.InMemoryResultStore;
import com.streampulse.store.ResultStore;

/**
 * Central Spring configuration for StreamPulse.
 *
 * Spring acts only as a wiring layer.
 * Core remains framework-agnostic.
 */
@Configuration
public class StreamPulseConfig {

    @Bean
    public DefaultStreamEngine streamEngine(WebSocketPublisher publisher) {
        DefaultStreamEngine engine = new DefaultStreamEngine();

        // Attach WebSocket output
        engine.addResultListener(publisher);

        return engine;
    }

    @Bean
    public ResultStore resultStore() {
        return new InMemoryResultStore();
    }

    @Bean
    public StreamPulse streamPulse(
            DefaultStreamEngine engine,
            ResultStore resultStore
    ) {
        StreamPulse pulse = new StreamPulse(engine, resultStore);

        // Register analytics modules
        pulse.registerAnalytics(new MovingAverage(5));
        pulse.registerAnalytics(new SpikeDetector(0.15));
        pulse.registerAnalytics(new AnomalyDetector(5, 3.0));

        return pulse;
    }
}
