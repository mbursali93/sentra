package com.example.sentra.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.sentra.model.ProductModel;
import com.example.sentra.repository.ProductRepository;
import com.example.sentra.service.ProductService;

public class ProductServiceTest {
    
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {

        // mockProductRepository = Mockito.mock(ProductRepository.class);
        // productService = new ProductService();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        
        ProductModel product = new ProductModel();
        // product.builder().description("").build();
        when(productRepository.findById("1")).thenReturn(Optional.of(null));

        ProductModel expectedProduct = this.productService.findOne("null", "");
        assertEquals("", "");
    }
}
