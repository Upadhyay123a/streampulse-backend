package com.streampulse.adapter.websocket;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.streampulse.model.AnalyticsResult;
import com.streampulse.output.ResultPublisher;

@Component
public class WebSocketPublisher implements ResultPublisher {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketPublisher(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void publish(AnalyticsResult result) {
        messagingTemplate.convertAndSend("/topic/analytics", result);
    }
}
