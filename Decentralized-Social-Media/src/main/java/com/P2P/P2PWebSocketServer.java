package com.P2P;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/ws")
@Component
public class P2PWebSocketServer {

    private static final Set<Session> sessions = ConcurrentHashMap.newKeySet();

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        System.out.println("New connection: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session sender) {
        sessions.stream()
            .filter(session -> !session.equals(sender)) // broadcast to others only
            .forEach(session -> {
                try {
                    session.getBasicRemote().sendText("From " + sender.getId() + ": " + message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        System.out.println("Disconnected: " + session.getId());
    }
}

