package com.example.sentra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.sentra.dto.UserDto;
import com.example.sentra.expections.CustomException;
import com.example.sentra.model.UserModel;
import com.example.sentra.repository.UserRepository;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserModel register(UserDto userDto) {

        return userService.create(userDto, "");
    }

    public String login(String username, String password) {

        UserModel user = userRepository.findByUsername(username);
        
        boolean passwordMatches = bCryptPasswordEncoder.matches(password, user.getPassword());

        if (!passwordMatches)
            throw new CustomException("Wrong credentials", "USER_NOT_FOUND");

        return "null";
    }

    public void logout() {

    }

    public String refreshToken() {

        return null;
    }
}
