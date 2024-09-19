package com.example.sentra.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sentra.base.BaseService;
import com.example.sentra.dto.CreateHomeDto;
import com.example.sentra.dto.UpdateHomeDto;
import com.example.sentra.expections.CustomException;
import com.example.sentra.model.HomeModel;
import com.example.sentra.repository.HomeRepository;

@Service
public class HomeService implements BaseService<HomeModel, CreateHomeDto> {

    @Autowired
    private HomeRepository homeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public HomeModel create(CreateHomeDto data, String userId) {
        String homeName = data.getHomeName();
        boolean homeNameExists = homeRepository.homeNameExistsForOwner(homeName, userId);
       
        if (homeNameExists)
            throw new CustomException(userId, homeName);
        
        HomeModel home = modelMapper.map(data, HomeModel.class);

        homeRepository.save(home);
        return home;
    }

    @Override
    public HomeModel delete(String id, Object tokenData) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public HomeModel findAll(String userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<HomeModel> findOne(String id, String userId) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public HomeModel update(String id, CreateHomeDto data, Object tokenData) {
        // TODO Auto-generated method stub
        return null;
    }
    
    
}
