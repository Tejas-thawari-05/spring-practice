package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

import com.P2P.P2PWebSocketHandler;

import jakarta.annotation.PostConstruct;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final P2PWebSocketHandler p2pWebSocketHandler;

    public WebSocketConfig(P2PWebSocketHandler p2pWebSocketHandler) {
        this.p2pWebSocketHandler = p2pWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(p2pWebSocketHandler, "/ws/posts")
                .setAllowedOrigins("*");
    }
    
    @PostConstruct
    public void init() {
        System.out.println("✅ WebSocket handler registered at /ws/posts");
    }
}
