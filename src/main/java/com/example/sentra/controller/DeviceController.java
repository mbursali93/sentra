package com.example.sentra.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin
public class DeviceController {

    @MessageMapping("/device/message")
    @SendTo("/topic/device") 
    public void sendMessage(String message) {
        System.out.println("Mesaj Alındı: ");
        
    }
}
