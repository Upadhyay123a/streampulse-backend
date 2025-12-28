package com.streampulse.adapter.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.streampulse.engine.DefaultStreamEngine;
import com.streampulse.model.DataPoint;

@RestController
@RequestMapping("/api/stream")
public class PriceRestController {

    private final DefaultStreamEngine engine;

    public PriceRestController(DefaultStreamEngine engine) {
        this.engine = engine;
    }

    @PostMapping("/ingest")
    public ResponseEntity<String> ingest(@RequestBody IngestRequest request) {

        if (request.getKey() == null || request.getKey().isBlank()) {
            return ResponseEntity.badRequest().body("key is required");
        }

        long timestamp = request.getTimestamp() != null
                ? request.getTimestamp()
                : System.currentTimeMillis();

        DataPoint point = new DataPoint(
                request.getKey(),
                request.getValue(),
                timestamp
        );

        engine.ingest(point);

        return ResponseEntity.ok("ingested");
    }
}
