package com.example.sentra.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.sentra.base.BaseService;
import com.example.sentra.dto.CreateProductDto;
import com.example.sentra.dto.UpdateProductDto;
import com.example.sentra.expections.ApiException;
import com.example.sentra.model.ProductModel;
import com.example.sentra.repository.ProductRepository;

@Service
public class ProductService implements BaseService<ProductModel, CreateProductDto, UpdateProductDto> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductModel create(CreateProductDto data, String userId) {
        // TODO Auto-generated method stub

        ProductModel product = modelMapper.map(data, ProductModel.class);

        return productRepository.save(product);
        
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

        Optional<ProductModel> product = productRepository.getProductById(id);
        if (!product.isPresent())
            throw new ApiException("No product found with this id", "NOT_FOUND", HttpStatus.NOT_FOUND);

        return product.get();
    }

    @Override
    public ProductModel update(String id, UpdateProductDto data, String userId) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
