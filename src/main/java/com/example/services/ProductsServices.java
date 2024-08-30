package com.example.services;

import com.example.dto.ProductDto;
import com.example.entites.Product;

import java.util.List;

public interface ProductsServices {

    Product createProduct(ProductDto productDto);

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product updateProduct(Long id, ProductDto productDto);

    void deleteProduct(Long id);

    List<Product> getByName(String name);

}
