package com.streampulse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.streampulse.analytics.AnomalyDetector;
import com.streampulse.analytics.MovingAverage;
import com.streampulse.analytics.SpikeDetector;
import com.streampulse.engine.DefaultStreamEngine;
import com.streampulse.store.InMemoryResultStore;
import com.streampulse.store.ResultStore;

@Configuration
public class StreamPulseConfig {

    @Bean
    public ResultStore resultStore() {
        return new InMemoryResultStore();
    }

    @Bean
    public DefaultStreamEngine streamEngine(ResultStore store) {
        DefaultStreamEngine engine = new DefaultStreamEngine();

        // Register analytics
        engine.register(new MovingAverage(5));
        engine.register(new SpikeDetector(0.08));
        engine.register(new AnomalyDetector(3.0));

        // Register store as listener
        engine.addResultListener(store::add);

        return engine;
    }
}
