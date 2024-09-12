package com.example.sentra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sentra.dto.UserDto;
import com.example.sentra.model.UserModel;
import com.example.sentra.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")

public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    UserModel create(@Valid @RequestBody UserDto createDto) {

        UserModel user = userService.create(createDto, null);

        return user;
    }
}
