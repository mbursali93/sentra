package com.example.sentra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sentra.base.BaseService;
import com.example.sentra.dto.CreateMemberDto;
import com.example.sentra.model.HomeModel;
import com.example.sentra.model.MemberModel;
import com.example.sentra.repository.HomeRepository;
import com.example.sentra.repository.MemberRepository;
import com.example.sentra.repository.UserRepository;

@Service
public class MemberService implements BaseService<MemberModel, CreateMemberDto, Object> {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private HomeRepository homeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public MemberModel create(CreateMemberDto data, String userId) {
        homeRepository.findById(userId);
        return null;
    }

    @Override
    public MemberModel delete(String id, String userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MemberModel findAll(String userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MemberModel findOne(String id, String userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MemberModel update(String id, Object data, String userId) {
        // TODO Auto-generated method stub
        return null;
    }
}
