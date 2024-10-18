package com.example.sentra.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import com.example.sentra.dto.CreateProductDto;
import com.example.sentra.expections.ApiException;
import com.example.sentra.model.ProductModel;
import com.example.sentra.repository.ProductRepository;
import com.example.sentra.service.ProductService;

public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {

        CreateProductDto productDto = new CreateProductDto("title", "desctiption", "brand", "model", 0.01);

        ProductModel product = new ProductModel("title", "desctiption", "brand", "model", 0.01);

        when(productRepository.save(any(ProductModel.class))).thenReturn(product);
        when(modelMapper.map(any(), any())).thenReturn(product);

        ProductModel savedProduct = productService.create(productDto, "");

        assertEquals(product.getTitle(), savedProduct.getTitle());
        assertEquals(product.getBrand(), savedProduct.getBrand());

    }

    @Test()
    void testFindOneProduct() {

        ProductModel product = new ProductModel();

        when(productRepository.getProductById("correct_id")).thenReturn(Optional.of(product));
        when(productRepository.getProductById("wrong_id")).thenReturn(Optional.empty());

        ProductModel foundProduct = productService.findOne("correct_id", null);
        assertEquals(foundProduct, product);

        ApiException exception = assertThrows(ApiException.class, () -> {
            productService.findOne("wrong_id", null);
        });

        assertEquals("No product found with this id", exception.getMessage());
        assertEquals("NOT_FOUND", exception.getErrorCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    // @Test
    // void testFindAllProducts() {

    // }

    @Test
    void testDeleteProduct() {

        // Arrange
        ProductModel arrangeProduct = new ProductModel();
        arrangeProduct.setDeleted(true);
        arrangeProduct.setDeletedAt(Instant.now());

        when(productRepository.getProductById("correct_id")).thenReturn(Optional.of(arrangeProduct));
        when(productRepository.getProductById("wrong_id")).thenReturn(Optional.empty());

        when(productRepository.deleteProduct("correct_id")).thenReturn(arrangeProduct);
        

        // Act
        ProductModel actProduct = productService.delete("correct_id");
        ApiException exception = assertThrows(ApiException.class, ()-> {
            productService.delete("wrong_id");
        });

        // Assert
      
        assertEquals(arrangeProduct.getIsDeleted(), actProduct.getIsDeleted());
        assertEquals(arrangeProduct.getDeletedAt(), actProduct.getDeletedAt());

        assertEquals("No product found with this id", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());


    }
}