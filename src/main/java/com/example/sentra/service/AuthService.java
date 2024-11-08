package com.example.sentra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.sentra.dto.auth.UserDto;
import com.example.sentra.exceptions.CustomException;
import com.example.sentra.model.UserModel;
import com.example.sentra.repository.UserRepository;
import com.example.sentra.utils.JwtUtil;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtUtil jwtUtil;


    public UserModel register(UserDto userDto) {

        String password = userDto.getPassword();
        String confirmPassword = userDto.getConfirmPassword();

        if (!password.equals(confirmPassword))
            throw new CustomException("Passwords do not match", "BAD_REQUEST");

        return userService.create(userDto, "");
    }

    public String login(String username, String password) {

        UserModel user = userRepository.findByUsername(username);
        
        boolean passwordMatches = bCryptPasswordEncoder.matches(password, user.getPassword());

        if (!passwordMatches)
            throw new CustomException("Wrong credentials", "USER_NOT_FOUND");

        String jwtToken = jwtUtil.generateToken(user.getId(), user.getIsAdmin());
        
        return jwtToken;
    }

    public void logout() {

    }

    public String refreshToken() {

        return null;
    }
}
