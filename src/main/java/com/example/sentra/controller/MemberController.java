package com.example.sentra.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sentra.dto.CreateMemberDto;
import com.example.sentra.model.MemberModel;
import com.example.sentra.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("member")
public class MemberController {

    @Autowired
    private MemberService memberService;
    
    @PostMapping
    public ResponseEntity<MemberModel> create(@Valid @RequestBody CreateMemberDto data, HttpServletRequest request) {

        String userId = (String) request.getAttribute("userId");

        MemberModel member = this.memberService.create(data, userId);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/{homeId}")
    public ResponseEntity<MemberModel> findOne(@PathVariable String homeId, HttpServletRequest request) {

        String userId = (String) request.getAttribute("userId");

        MemberModel member = memberService.findOne(homeId, userId);
        return ResponseEntity.ok(member);
    }
}
