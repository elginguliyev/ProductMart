package com.example.services.impl;

import com.example.request.CategoryRequest;
import com.example.entites.Category;
import com.example.repository.CategoryRepository;
import com.example.response.CategoryResponse;
import com.example.services.inter.CategoryServices;
import com.example.dto.CategoryToCategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServicesImpl implements CategoryServices {



    private  final CategoryRepository categoryRepository;

    public CategoryServicesImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Category createCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        return categoryRepository.save(category);
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category  not found"));

        CategoryResponse categoryResponse = CategoryToCategoryResponse.concertToCategory(category);
        return categoryResponse;
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream()
                .map(category -> CategoryToCategoryResponse.concertToCategory(category))
                .collect(Collectors.toList());

    }

    @Override
    public void updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category  not found"));
        category.setName(categoryRequest.getName());
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
