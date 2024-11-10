package com.example.services;

import com.example.dto.ProductDto;
import com.example.entites.Product;

import java.util.List;

public interface ProductsServices {

    Product createProduct(ProductDto productDto);

    ProductDto getProductById(Long id);

    List<ProductDto> getAllProducts();

    void updateProduct(Long id, ProductDto productDto);

    void deleteProduct(Long id);

    List<ProductDto> getByName(String name);

}
