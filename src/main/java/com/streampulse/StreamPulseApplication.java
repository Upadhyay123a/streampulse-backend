package com.streampulse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StreamPulseApplication {
    public static void main(String[] args) {
        SpringApplication.run(StreamPulseApplication.class, args);
    }
}
