package com.example.sentra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @GetMapping
    public ResponseEntity<Page<ProductModel>> findAll(@RequestParam(required = false) String filter,  @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size, @RequestParam(required = false) String search,  @RequestParam(defaultValue = "0") int min,
            @RequestParam int max) {

        productService.findAll(filter, search, min, max, page, size);
        return null;
    }

}
