package com.example.sentra.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class DeviceHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Triggered when a connection is established
        System.out.println("Connected: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Process received messages
        String payload = message.getPayload();
        System.out.println("Received: " + payload);
        
        // Send a response to the client
        session.sendMessage(new TextMessage("Hello! Message received: " + payload));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Triggered when a connection is closed
        System.out.println("Disconnected: " + session.getId());
    }
}
