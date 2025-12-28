package com.streampulse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.streampulse.analytics.AnomalyDetector;
import com.streampulse.analytics.MovingAverage;
import com.streampulse.analytics.SpikeDetector;
import com.streampulse.engine.DefaultStreamEngine;
import com.streampulse.output.ResultPublisher;

/**
 * Central Spring configuration for StreamPulse.
 *
 * This config wires:
 *  - Core StreamEngine
 *  - Built-in analytics modules
 *  - Output publisher
 *
 * NOTE:
 * This is Spring-only glue code.
 * Core engine remains framework-agnostic.
 */
@Configuration
public class StreamPulseConfig {

    @Bean
    public DefaultStreamEngine streamEngine(ResultPublisher publisher) {

        DefaultStreamEngine engine = new DefaultStreamEngine();

        // ---- Register analytics modules ----
        engine.register(new MovingAverage(5));
        engine.register(new SpikeDetector(0.15));
        engine.register(new AnomalyDetector(5, 3.0));

        // ---- Attach output channel ----
        engine.addResultListener(publisher::publish);

        return engine;
    }
}
