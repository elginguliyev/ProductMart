package com.example.utill;

import com.example.dto.CategoryDto;
import com.example.dto.ProductDto;
import com.example.entites.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryToCategoryDto {

    public static CategoryDto concertToCategory(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        List<ProductDto> productDtoList = category.getProducts().stream()
                .map(product -> ProductToProductDto.convertToProduct(product))
                .collect(Collectors.toList());

        categoryDto.setProductDtoList(productDtoList);
        return categoryDto;
    }
}

