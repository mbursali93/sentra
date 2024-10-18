package com.example.sentra.service;

import java.util.ArrayList;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.sentra.base.BaseService;
import com.example.sentra.dto.CreateProductDto;
import com.example.sentra.dto.UpdateProductDto;
import com.example.sentra.expections.ApiException;
import com.example.sentra.model.ProductModel;
import com.example.sentra.repository.ProductRepository;

@Service
public class ProductService  {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    
    public ProductModel create(CreateProductDto data, String userId) {
        // TODO Auto-generated method stub

        ProductModel product = modelMapper.map(data, ProductModel.class);

        return productRepository.save(product);
        
    }

    
    public ProductModel delete(String id) {
        // TODO Auto-generated method stub
        ProductModel product = this.findOne(id, "");
        
        product = productRepository.deleteProduct(id);
        return product;
    }


    public Page<ProductModel> findAll(String filter, String search, int min, int max, int page, int size) {
        // TODO Auto-generated method stub
        return null;
    }

    
    public ProductModel findOne(String id, String userId) {
        // TODO Auto-generated method stub

        Optional<ProductModel> product = productRepository.getProductById(id);
        if (!product.isPresent())
            throw new ApiException("No product found with this id", "NOT_FOUND", HttpStatus.NOT_FOUND);

        return product.get();
    }

    public ProductModel update(String id, UpdateProductDto data, String userId) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
