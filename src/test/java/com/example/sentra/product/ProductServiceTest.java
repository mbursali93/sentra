package com.example.sentra.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
import com.example.sentra.dto.UpdateProductDto;
import com.example.sentra.exceptions.ApiException;
import com.example.sentra.model.ProductModel;
import com.example.sentra.repository.ProductRepository;
import com.example.sentra.service.ProductService;
import com.mongodb.client.model.MergeOptions.WhenNotMatched;

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

        ProductModel foundProduct = productService.findOne("correct_id");
        assertEquals(foundProduct, product);

        ApiException exception = assertThrows(ApiException.class, () -> {
            productService.findOne("wrong_id");
        });

        assertEquals("No product found with this id", exception.getMessage());
        assertEquals("NOT_FOUND", exception.getErrorCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    // @Test
    // void testFindAllProducts() {

    // }

    
    @Test
void updateProduct() {
    // Arrange
    UpdateProductDto dto = new UpdateProductDto();
    dto.setBrand("new_brand");

    ProductModel arrangeProduct = new ProductModel();
    arrangeProduct.setBrand(dto.getBrand());
    
    ProductModel updatedArrangeProduct = arrangeProduct;
    updatedArrangeProduct.setUpdatedAt(Instant.now());

    // Use argument matchers to ensure mock returns expected value
    when(productRepository.getProductById("correct_id")).thenReturn(Optional.of(arrangeProduct));
    when(productRepository.updateProduct(eq("correct_id"), any(ProductModel.class)))
        .thenReturn(updatedArrangeProduct);
    when(modelMapper.map(any(UpdateProductDto.class), eq(ProductModel.class)))
        .thenReturn(arrangeProduct);

    // Act
    ProductModel actProduct = productService.update("correct_id", dto);

    // Assert
    assertEquals(updatedArrangeProduct.getUpdatedAt(), actProduct.getUpdatedAt());
}


    
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
        ApiException exception = assertThrows(ApiException.class, () -> {
            productService.delete("wrong_id");
        });

        // Assert

        assertEquals(arrangeProduct.getIsDeleted(), actProduct.getIsDeleted());
        assertEquals(arrangeProduct.getDeletedAt(), actProduct.getDeletedAt());

        assertEquals("No product found with this id", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

    }
}