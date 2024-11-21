package com.example.dto;

import com.example.request.CategoryRequest;
import com.example.request.ProductRequest;
import com.example.entites.Category;
import com.example.response.CategoryResponse;
import com.example.response.ProductResponse;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryToCategoryResponse {

    public static CategoryResponse concertToCategory(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        List<ProductResponse> responseList = category.getProducts().stream()
                .map(product -> ProductToProductResponse.convertToProduct(product))
                .collect(Collectors.toList());

        categoryResponse.setProducts(responseList);
        return categoryResponse;
    }
}

