package com.streampulse;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.streampulse.analytics.AnomalyDetector;
import com.streampulse.analytics.MovingAverage;
import com.streampulse.analytics.SpikeDetector;
import com.streampulse.engine.DefaultStreamEngine;
import com.streampulse.model.DataPoint;
import com.streampulse.output.ResultPublisher;

/**
 * TEMP demo runner for StreamPulse.
 * This class exists only to demonstrate library usage.
 */
@SpringBootApplication
public class StreamPulseApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamPulseApplication.class, args);
    }

    @Bean
    CommandLineRunner demoRunner(ResultPublisher publisher) {
        return args -> {

            DefaultStreamEngine engine = new DefaultStreamEngine();

            // Register analytics
            engine.register(new MovingAverage(5));
            engine.register(new SpikeDetector(0.05));
            engine.register(new AnomalyDetector(10, 3.0));

            // Register output
            engine.addResultListener(publisher::publish);

            // Simulated stream
            long now = System.currentTimeMillis();
            double[] values = {
                    100, 101, 102, 100, 99,
                    98, 200, 101, 100, 99
            };

            for (double v : values) {
                engine.ingest(new DataPoint("DEMO_STREAM", v, now));
                Thread.sleep(500);
            }
        };
    }
}
