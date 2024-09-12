package com.example.sentra.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    
    @NotBlank(message = "Password is required")
    @Size(min=3, max = 5)
    private String username;

    @NotBlank(message = "Password is required")
    @Email()
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Password is required")
    private String confirmPassword;
}
