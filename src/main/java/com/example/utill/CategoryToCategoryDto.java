package com.example.utill;

import com.example.dto.CategoryDto;
import com.example.entites.Category;

public class CategoryToCategoryDto {

    public static CategoryDto concertToCategory(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        return categoryDto;
    }
}

