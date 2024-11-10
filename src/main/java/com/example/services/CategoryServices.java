package com.example.services;

import com.example.dto.CategoryDto;
import com.example.entites.Category;

import java.util.List;

public interface CategoryServices {

    Category createCategory(CategoryDto categoryDto);

    CategoryDto getCategoryById(Long id);

    List<CategoryDto> getAllCategories();

    void updateCategory(Long id, CategoryDto categoryDto);

    void deleteCategory(Long id);
}
