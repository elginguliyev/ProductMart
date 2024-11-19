package com.example.controller;

import com.example.request.CategoryRequest;
import com.example.entites.Category;
import com.example.response.CategoryResponse;
import com.example.services.inter.CategoryServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/")
public class CatagoryController {

    private CategoryServices categoryServices;

    public CatagoryController(CategoryServices categoryServices) {
        this.categoryServices = categoryServices;
    }

    @GetMapping(path = "catgeories")
    public ResponseEntity<List<CategoryResponse>> getAllCategory() {
        List<CategoryResponse> categoryResponses = categoryServices.getAllCategories();
        return ResponseEntity.ok(categoryResponses);
    }

    @GetMapping(path = "category/{id}")
    public ResponseEntity<CategoryResponse> getByIdCategory(@PathVariable Long id) {
        CategoryResponse categoryResponse = categoryServices.getCategoryById(id);
        return ResponseEntity.ok(categoryResponse);
    }

    @DeleteMapping("category/{id}")
    public void deleteByIDCategory(@PathVariable Long id) {
        categoryServices.deleteCategory(id);
    }

    @PutMapping(path = "category/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id,
                                                           @RequestBody CategoryRequest categoryRequest) {
        categoryServices.updateCategory(id, categoryRequest);
        CategoryResponse categoryResponse = getByIdCategory(id).getBody();
        return ResponseEntity.ok(categoryResponse);
    }
@PatchMapping @PutMapping
    @PostMapping(path = "add/category")
    public ResponseEntity<String> addCategory(@RequestBody CategoryRequest categoryRequest) {
        Category category = categoryServices.createCategory(categoryRequest);
        return ResponseEntity.ok(category.getName() + " created successfully");
    }
}
