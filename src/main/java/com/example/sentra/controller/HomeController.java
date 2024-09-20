package com.example.sentra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sentra.dto.CreateHomeDto;
import com.example.sentra.model.HomeModel;
import com.example.sentra.service.HomeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RequestMapping("/home")
@RestController
public class HomeController {

    @Autowired
    private HomeService homeService;
    
    @PostMapping
    public ResponseEntity<HomeModel> create(@Valid @RequestBody CreateHomeDto data, HttpServletRequest request) {

        String userId = (String) request.getAttribute("userId");

        // System.out.println(userId);

        HomeModel home = homeService.create(data, userId);

        return ResponseEntity.ok(home);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HomeModel> delete(@PathVariable String id) {
        HomeModel home = homeService.delete(id, id);
        return ResponseEntity.ok(home);
    }

}
