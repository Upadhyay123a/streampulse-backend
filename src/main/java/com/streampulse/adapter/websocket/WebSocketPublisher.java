package com.streampulse.adapter.websocket;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.streampulse.api.ResultListener;
import com.streampulse.model.AnalyticsResult;
import com.streampulse.output.ResultPublisher;

/**
 * WebSocket adapter that publishes analytics results
 * to subscribed clients in real time.
 */
@Component
public class WebSocketPublisher implements ResultPublisher, ResultListener {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketPublisher(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void publish(AnalyticsResult result) {
        messagingTemplate.convertAndSend("/topic/analytics", result);
    }

    @Override
    public void onResult(AnalyticsResult result) {
        publish(result);
    }
}
