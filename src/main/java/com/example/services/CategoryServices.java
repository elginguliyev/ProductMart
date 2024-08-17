package com.example.services;

import com.example.entites.Category;

import java.util.List;

public interface CategoryServices {

    Category createCategory(Category category);

    Category getCategoryById(Long id);

    List<Category> getAllCategories();

    Category updateCategory(Long id, Category category);

    void deleteCategory(Long id);
}
