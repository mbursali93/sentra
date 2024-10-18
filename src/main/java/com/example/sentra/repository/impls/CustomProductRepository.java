package com.example.sentra.repository.impls;

import java.util.Optional;

import com.example.sentra.model.ProductModel;

public interface CustomProductRepository {
    Optional<ProductModel> getProductById(String id);

    public ProductModel deleteProduct(String id);
}
