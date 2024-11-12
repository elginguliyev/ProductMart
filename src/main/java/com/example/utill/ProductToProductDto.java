package com.example.utill;

import com.example.dto.ProductDto;
import com.example.entites.Product;

public class ProductToProductDto {

    public static ProductDto convertToProduct(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());
        productDto.setCategoryId(product.getCatagory().getId());
        return productDto;
    }
}
