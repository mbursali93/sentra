package com.example.sentra.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateProductDto {
    @Size(min = 3)
    @Nullable
    private String title;

    @Size(max = 255)
    @Nullable
    private String description;

    @Nullable
    private String brand;

    @Nullable
    private String model;

    @Nullable
    // @DecimalMin(value = "0.01", inclusive = true)
    private double price;

    

}
