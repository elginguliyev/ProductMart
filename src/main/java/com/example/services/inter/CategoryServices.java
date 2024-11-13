package com.example.services.inter;

import com.example.request.CategoryRequest;
import com.example.entites.Category;
import com.example.response.CategoryResponse;

import java.util.List;

public interface CategoryServices {

    Category createCategory(CategoryRequest categoryRequest);

    CategoryResponse getCategoryById(Long id);

    List<CategoryResponse> getAllCategories();

    void updateCategory(Long id, CategoryRequest categoryRequest);

    void deleteCategory(Long id);
}
