package com.streampulse.service;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.streampulse.model.PriceUpdate;

@Service
public class PriceHistoryService {

    private static final int HISTORY_LIMIT = 200;

    private final Map<String, Deque<PriceUpdate>> history = new HashMap<>();

    public void add(PriceUpdate price) {
        history.putIfAbsent(price.getSymbol(), new ArrayDeque<>());
        Deque<PriceUpdate> list = history.get(price.getSymbol());

        list.addLast(price);
        if (list.size() > HISTORY_LIMIT) {
            list.removeFirst();
        }
    }

    public Map<String, Deque<PriceUpdate>> getAllLatest() {
        return history;
    }

    public Deque<PriceUpdate> getHistory(String symbol) {
        return history.getOrDefault(symbol, new ArrayDeque<>());
    }
}
