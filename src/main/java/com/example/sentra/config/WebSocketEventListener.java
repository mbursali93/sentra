package com.example.sentra.config;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.HashMap;
import java.util.Map;

@Component
public class WebSocketEventListener {

    private final Map<String, String> activeUsers = new HashMap<>(); 

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {

        System.out.println("bağlandı");
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();
        

        String username = headerAccessor.getFirstNativeHeader("username");

        if (username != null) {
            activeUsers.put(sessionId, username);
            System.out.println("Bağlanan Kullanıcı: " + username + " (ID: " + sessionId + ")");
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();

        String username = activeUsers.remove(sessionId);

        if (username != null) {
            System.out.println("Ayrılan Kullanıcı: " + username + " (ID: " + sessionId + ")");
        }
    }

    public Map<String, String> getActiveUsers() {
        return activeUsers;
    }
}
