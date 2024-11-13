package com.example.services.inter;

import com.example.request.ProductRequest;
import com.example.entites.Product;
import com.example.response.ProductResponse;

import java.io.IOException;
import java.util.List;

public interface ProductsServices {

    ProductResponse createProduct(ProductRequest productRequest) throws IOException;

    ProductResponse getProductById(Long id);

    List<ProductResponse> getAllProducts();

    void updateProduct(Long id, ProductRequest productRequest);

    void deleteProduct(Long id);

    List<ProductResponse> getByName(String name);

}
