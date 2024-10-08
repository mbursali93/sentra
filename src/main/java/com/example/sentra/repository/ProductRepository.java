package com.example.sentra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.sentra.model.ProductModel;
import com.example.sentra.repository.impls.CustomProductRepository;

public interface ProductRepository extends MongoRepository<ProductModel, String>, CustomProductRepository{
    
}
