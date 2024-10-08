package com.example.sentra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sentra.base.BaseService;
import com.example.sentra.dto.CreateProductDto;
import com.example.sentra.dto.UpdateProductDto;
import com.example.sentra.model.ProductModel;
import com.example.sentra.repository.ProductRepository;

@Service
public class ProductService implements BaseService<ProductModel, CreateProductDto, UpdateProductDto> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductModel create(CreateProductDto data, String userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProductModel delete(String id, String userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProductModel findAll(String userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProductModel findOne(String id, String userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProductModel update(String id, UpdateProductDto data, String userId) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
