package com.example.sentra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.example.sentra.utils.JwtUtil;
import com.example.sentra.utils.SocketSessions;

import java.util.HashMap;
import java.util.Map;

@Component
public class WebSocketEventListener {

    @Autowired
    private SocketSessions socketSessions;

    @Autowired
    private JwtUtil jwtUtil;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {

        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String sessionId = headerAccessor.getSessionId();
        String jwtToken = headerAccessor.getFirstNativeHeader("Authorization");
        
        String userId = this.jwtUtil.extractUserId(jwtToken);
        

        socketSessions.addUser(userId, sessionId);
        System.out.println("User: " + userId + " has connected with id: " + sessionId);
        
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        
        String jwtToken = headerAccessor.getFirstNativeHeader("Authorization");
        
        if (jwtToken != null) {       
            
            String userId = this.jwtUtil.extractUserId(jwtToken);
            String sessionId = this.socketSessions.getUser(userId);
            System.out.println("User: " + userId + " has disconnected with id: " + sessionId);

        }
        
    }

}
