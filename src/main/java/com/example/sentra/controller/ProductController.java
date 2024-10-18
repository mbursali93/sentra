package com.example.sentra.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sentra.dto.UpdateProductDto;
import com.example.sentra.model.ProductModel;
import com.example.sentra.service.ProductService;

import jakarta.validation.Valid;

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

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> findOne(@PathVariable String id) {
        ProductModel product = this.productService.findOne(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> update(@PathVariable String id, @Valid @RequestBody UpdateProductDto body) {
        ProductModel updatedProduct = this.productService.update(id, body);
        return ResponseEntity.ok(updatedProduct);
    }
}
