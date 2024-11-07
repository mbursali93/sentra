package com.example.sentra.interceptors;

import com.example.sentra.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Component
public class RmeSessionChannelInterceptor implements ChannelInterceptor {

    private final JwtUtil jwtUtil;

    @Autowired
    public RmeSessionChannelInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        MessageHeaders headers = message.getHeaders();
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        MultiValueMap<String, String> multiValueMap = headers.get(StompHeaderAccessor.NATIVE_HEADERS, MultiValueMap.class);

        if (multiValueMap != null) {
            List<String> authorizationHeaders = multiValueMap.get("Authorization");

            if (authorizationHeaders != null && !authorizationHeaders.isEmpty()) {
                String tokenWithBearer = authorizationHeaders.get(0);      
                String token = tokenWithBearer.startsWith("Bearer ") ? tokenWithBearer.substring(7) : tokenWithBearer;  

                try {
                    String userId = jwtUtil.extractUserId(token);

                    if (jwtUtil.validateToken(token, userId)) {
                       
                        accessor.setUser(() -> userId); 
                    } else {
                        System.out.println("Invalid token");
                        throw new IllegalArgumentException("Invalid token");
                    }
                } catch (Exception e) {
                    System.err.println("Token validation error: " + e.getMessage());
                    throw new IllegalArgumentException("Token validation failed", e);
                }
            } else {
              
                throw new IllegalArgumentException("Authorization header missing");
            }
        }
        return message;
    }
}
