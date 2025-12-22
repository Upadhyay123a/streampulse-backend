package com.streampulse.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.streampulse.model.PriceUpdate;

@Controller
public class PublishController {

    @MessageMapping("/echo")
    @SendTo("/topic/echo")
    public PriceUpdate echo(PriceUpdate update) {
        return update;
    }
}
