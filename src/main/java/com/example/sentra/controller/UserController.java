package com.example.sentra.controller;

// import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/{id}")
    Optional<UserModel> findOne(@PathVariable String id) {

        System.out.println("\n ALDINUZ Mİ");
        System.out.println(id);
        Optional<UserModel> user = userService.findOne(id, id);

        return user;
    }

    @GetMapping()
    List<UserModel> findAll(@RequestParam(required = false, defaultValue = "1") String page, @RequestParam(required = false) String dalga) {

        System.out.println(page);
        return null;
    }
}
