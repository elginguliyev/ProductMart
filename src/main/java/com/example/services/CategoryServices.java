package com.example.services;

import com.example.dto.CategoryDto;
import com.example.entites.Category;

import java.util.List;

public interface CategoryServices {

    Category createCategory(CategoryDto categoryDto);

    Category getCategoryById(Long id);

    List<Category> getAllCategories();

    Category updateCategory(Long id, CategoryDto categoryDto);

    void deleteCategory(Long id);
}
