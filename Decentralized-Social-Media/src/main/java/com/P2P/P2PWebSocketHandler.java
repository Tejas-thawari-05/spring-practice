package com.P2P;




import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class P2PWebSocketHandler extends TextWebSocketHandler {

    private static final Set<WebSocketSession> peers = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        peers.add(session);
        System.out.println("🟢 Peer connected: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("📨 Message from peer [" + session.getId() + "]: " + message.getPayload());
        broadcast(message.getPayload(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        peers.remove(session);
        System.out.println("🔴 Peer disconnected: " + session.getId());
    }

    private void broadcast(String message, WebSocketSession sender) throws Exception {
        for (WebSocketSession peer : peers) {
            if (peer.isOpen() && !peer.getId().equals(sender.getId())) {
                peer.sendMessage(new TextMessage(message));
            }
        }
    }
}
