package com.example.sentra.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sentra.base.BaseService;
import com.example.sentra.dto.UserDto;
import com.example.sentra.model.UserModel;
import com.example.sentra.repository.UserRepository;

@Service
public class UserService implements BaseService<UserModel, UserDto> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserModel create(UserDto data, String id) {
        // TODO Auto-generated method stub

        // Add bcrypt
        
        UserModel userData = modelMapper.map(data, UserModel.class);

        return userRepository.save(userData);

    }

    @Override
    public UserModel findOne(String id, Object tokenData) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserModel findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserModel update(String id, UserDto data, Object tokenData) {
        // TODO Auto-generated method stub
        return null;
    }

    
    @Override
    public UserModel delete(String id, Object tokenData) {
        // TODO Auto-generated method stub
        return null;
    }
   

}

