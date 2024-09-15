package com.example.sentra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sentra.model.UserModel;
import com.example.sentra.repository.UserRepository;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public UserModel register() {

        return null;
    }

    public String login() {

        return null;
    }

    public void logout() {

    }

    public String refreshToken() {

        return null;
    }
}
