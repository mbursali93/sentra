package com.example.sentra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sentra.model.ProductModel;
import com.example.sentra.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private  ProductService productService;
    
    @PostMapping("/")
    public ResponseEntity<ProductModel> create() {

        
        productService.create(null, null);
        return null;
    }

}
