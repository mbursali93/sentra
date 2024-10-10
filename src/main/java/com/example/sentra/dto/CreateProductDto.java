package com.example.sentra.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateProductDto {
    @Size(min = 3)
    @NotNull
    private String title;

    @Size(max = 255)
    private String description;

    @NotNull
    private String brand;

    @NotNull
    private String model;

    @NotNull
    @DecimalMin(value = "0.01", inclusive = true)
    private double price;

    

}
