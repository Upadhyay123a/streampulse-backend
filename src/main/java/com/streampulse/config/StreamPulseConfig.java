package com.streampulse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.streampulse.adapter.websocket.WebSocketPublisher;
import com.streampulse.analytics.AnomalyDetector;
import com.streampulse.analytics.MovingAverage;
import com.streampulse.analytics.SpikeDetector;
import com.streampulse.core.StreamPulse;

/**
 * Central Spring configuration for StreamPulse.
 *
 * This config wires:
 *  - Core StreamPulse engine (framework-agnostic)
 *  - Built-in analytics modules
 *  - Output publisher
 *
 * NOTE:
 * Spring is only acting as an adapter now.
 */
@Configuration
public class StreamPulseConfig {

    @Bean
    public StreamPulse streamPulse(WebSocketPublisher publisher) {
        // ---- Instantiate core library ----
        StreamPulse pulse = new StreamPulse();

        // ---- Register analytics modules ----
        pulse.registerAnalytics(new MovingAverage(5));
        pulse.registerAnalytics(new SpikeDetector(0.15));
        pulse.registerAnalytics(new AnomalyDetector(5, 3.0));

        // ---- Attach output channel (WebSocketPublisher) ----
        pulse.addResultListener(publisher::publish);

        return pulse;
    }
}
