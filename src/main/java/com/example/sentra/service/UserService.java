package com.example.sentra.service;

import javax.swing.text.html.Option;

// import org.apache.el.stream.Optional;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.sentra.base.BaseService;
import com.example.sentra.dto.auth.UserDto;
import com.example.sentra.model.UserModel;
import com.example.sentra.repository.UserRepository;

@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // @Override
    public UserModel create(UserDto data, String id) {
        
        UserModel userData = modelMapper.map(data, UserModel.class);

        String password = userData.getPassword();
        String hashedPassword = bCryptPasswordEncoder.encode(password);

        userData.setPassword(hashedPassword);

        return userRepository.save(userData);

    }

    // @Override
    public Optional<UserModel> findOne(String id, Object tokenData) {
        // TODO Auto-generated method stub

    Optional<UserModel> optionalUser = userRepository.findById(id);

        return optionalUser;
    }

    // @Override
    public UserModel findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    // @Override
    public UserModel update(String id, UserDto data, Object tokenData) {
        // TODO Auto-generated method stub
        return null;
    }

    
    // @Override
    public UserModel delete(String id, Object tokenData) {
        // TODO Auto-generated method stub
        return null;
    }
   

}

