package com.example.sentra.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginDto {
    
    String username;
    String password;
}
