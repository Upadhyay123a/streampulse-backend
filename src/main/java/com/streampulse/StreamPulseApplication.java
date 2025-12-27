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

@SpringBootApplication
public class StreamPulseApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamPulseApplication.class, args);
    }

    /**
     * Demo runner – wires engine + analytics + publisher.
     * This will be removed when library is published.
     */
    @Bean
    CommandLineRunner demoRunner(ResultPublisher publisher) {
        return args -> {

            DefaultStreamEngine engine = new DefaultStreamEngine();

            // Register analytics
            engine.register(new MovingAverage(5));
            engine.register(new SpikeDetector(0.15));
            engine.register(new AnomalyDetector(3.0));

            // Attach output
            engine.addResultListener(publisher::publish);

            // Simulate streaming data
            double[] values = {100, 101, 102, 110, 98, 97, 140, 99, 100};

            for (double v : values) {
                engine.ingest(
                        new DataPoint(
                                "DEMO_STREAM",
                                v,
                                System.currentTimeMillis()
                        )
                );
                Thread.sleep(500);
            }
        };
    }
}
