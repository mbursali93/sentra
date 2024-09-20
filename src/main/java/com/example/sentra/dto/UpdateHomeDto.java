package com.example.sentra.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateHomeDto {
    
    @Size(min = 3, max = 55)
    private String homeName;
}
