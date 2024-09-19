package com.example.sentra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sentra.dto.auth.LoginDto;
import com.example.sentra.dto.auth.UserDto;
import com.example.sentra.model.UserModel;
import com.example.sentra.service.AuthService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public UserModel register(@Valid @RequestBody UserDto data) {

        return authService.register(data);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto data) {
        String username = data.getUsername();
        String password = data.getPassword();

        return authService.login(username, password);
    }
}

