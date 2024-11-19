package com.example.PacMan;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ThingWebSocketHandler thingWebSocketHandler;

    public WebSocketConfig(ThingWebSocketHandler thingWebSocketHandler) {
        this.thingWebSocketHandler = thingWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(thingWebSocketHandler, "/ws").setAllowedOrigins("*");
    }
}

