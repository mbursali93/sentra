package com.example.sentra.service;

import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sentra.base.BaseService;
import com.example.sentra.dto.CreateHomeDto;
import com.example.sentra.dto.CreateMemberDto;
import com.example.sentra.dto.UpdateHomeDto;
import com.example.sentra.expections.CustomException;
import com.example.sentra.model.HomeModel;
import com.example.sentra.repository.HomeRepository;

@Service
public class HomeService implements BaseService<HomeModel, CreateHomeDto, UpdateHomeDto> {

    @Autowired
    private HomeRepository homeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MemberService memberService;

    @Override
    public HomeModel create(CreateHomeDto data, String userId) {
        String homeName = data.getHomeName();
        boolean homeNameExists = homeRepository.homeNameExistsForOwner(homeName, userId);

        if (homeNameExists)
            throw new CustomException(userId, homeName);
        
        HomeModel home = modelMapper.map(data, HomeModel.class);
        home.setOwnerId(userId);

        homeRepository.save(home);

        CreateMemberDto member = new CreateMemberDto();
        member.setHomeId(home.getId());
        member.setMemberId(userId);
        member.setRoleLevel((byte) 10);

        memberService.create(member, userId);
        return home;
    }

    @Override
    public HomeModel delete(String id, String userId) {
        HomeModel home = this.findOne(id, userId);

        if (!home.getOwnerId().equals(userId))
            return null; // TODO: Error Message

        return homeRepository.delete(id);

    }

    @Override
    public HomeModel findAll(String userId) {
        return null;
    }

    @Override
    public HomeModel findOne(String id, String userId) {
        // TODO: check if user is the member of the home

        return homeRepository.findById(id).orElseThrow(()-> new CustomException(id, userId));
    }

    @Override
    public HomeModel update(String id, UpdateHomeDto data, String userId) {
        this.findOne(id, userId);
        String homeName = data.getHomeName();

        return homeRepository.updateHomeName(id, homeName);

    }
    
    
}
