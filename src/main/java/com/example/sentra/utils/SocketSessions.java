package com.example.sentra.utils;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class SocketSessions {
    
    private HashMap<String, String> socketUsers = new HashMap<>();

    public void addUser(String userId, String socketId) {

        if(!socketUsers.containsKey(userId))
            socketUsers.put(userId, socketId);
    }

    public String getUser(String userId) {

        if (socketUsers.containsKey(userId))
            return socketUsers.get(userId);
        
        return null;
    }
}
