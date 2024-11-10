package com.example.services.impl;

import com.example.dto.ProductDto;
import com.example.entites.Category;
import com.example.entites.Product;
import com.example.repository.CategoryRepository;
import com.example.repository.ProductRepository;
import com.example.services.ProductsServices;
import com.example.utill.ProductToProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServicesImpl implements ProductsServices {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Product createProduct(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCatagoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(product.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setCatagory(category);

        return productRepository.save(product);
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductDto productDto = ProductToProductDto.convertToProduct(product);
        return productDto;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .map(product -> ProductToProductDto.convertToProduct(product))
                .collect(Collectors.toList());
    }

    @Override
    public void updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepository.findById(productDto.getCatagoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setCatagory(category);
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> getByName(String name) {
        List<Product> productList = productRepository.findByName(name);
        return null;
    }
}
