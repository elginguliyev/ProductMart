package com.example.controller;

import com.example.dto.CategoryDto;
import com.example.entites.Category;
import com.example.services.inter.CategoryServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/")
public class CatagoryController {

    private CategoryServices categoryServices;

    public CatagoryController(CategoryServices categoryServices) {
        this.categoryServices = categoryServices;
    }

    @GetMapping(path = "catgeories")
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        List<CategoryDto> categoryDtos = categoryServices.getAllCategories();
        return ResponseEntity.ok(categoryDtos);
    }

    @GetMapping(path = "category/{id}")
    public ResponseEntity<CategoryDto> getByIdCategory(@PathVariable Long id) {
        CategoryDto categoryDto = categoryServices.getCategoryById(id);
        return ResponseEntity.ok(categoryDto);
    }

    @DeleteMapping("category/{id}")
    public void deleteByIDCategory(@PathVariable Long id) {
        categoryServices.deleteCategory(id);
    }

    @PutMapping(path = "category/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id,
                                                      @RequestBody CategoryDto categoryDto) {
        categoryServices.updateCategory(id, categoryDto);
        CategoryDto categoryDto1 = getByIdCategory(id).getBody();
        return ResponseEntity.ok(categoryDto1);
    }

    @PostMapping(path = "/add/category")
    public ResponseEntity<String> addCategory(@RequestBody CategoryDto categoryDto) {
        Category category = categoryServices.createCategory(categoryDto);
        return ResponseEntity.ok(category.getName() + " created successfully");
    }
}
