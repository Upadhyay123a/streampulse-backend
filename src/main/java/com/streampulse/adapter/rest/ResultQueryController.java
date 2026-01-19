package com.streampulse.adapter.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.streampulse.model.AnalyticsResult;
import com.streampulse.store.ResultStore;

@RestController
@RequestMapping("/api/results")
public class ResultQueryController {

    private final ResultStore store;

    public ResultQueryController(ResultStore store) {
        this.store = store;
    }

    @GetMapping
    public List<AnalyticsResult> getAllResults() {
        return store.findAll();
    }
}
