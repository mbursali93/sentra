package com.example.sentra.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.sentra.base.BaseModel;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document(collection = "products")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductModel extends BaseModel {
    
    @NotNull
    private String title;

    private String description;

    @NotNull
    private String brand;

    @NotNull
    private String model;

    @NotNull
    private Double price;

    // required materials?



}
