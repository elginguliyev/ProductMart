package com.example.services.impl;

import com.example.dto.ProductDto;
import com.example.entites.Category;
import com.example.entites.Product;
import com.example.repository.CategoryRepository;
import com.example.repository.ProductRepository;
import com.example.services.inter.ProductsServices;
import com.example.utill.ProductToProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServicesImpl implements ProductsServices {


    private final ProductRepository productRepository;


    private final CategoryRepository categoryRepository;

    public ProductServicesImpl(ProductRepository productRepository,
                               CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice() != null ? productDto.getPrice() : 0.0);
        product.setQuantity(productDto.getQuantity() != null ? productDto.getQuantity() : 0);
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


        if (productDto.getName() != null) {
            product.setName(productDto.getName());
        }
        if (productDto.getDescription() != null) {
            product.setDescription(productDto.getDescription());
        }
        if (productDto.getPrice() != null) {
            product.setPrice(productDto.getPrice());
        } else if (product.getPrice() == null) {
            product.setPrice(0.0);
        }
        if (productDto.getQuantity() != null) {
            product.setQuantity(productDto.getQuantity());
        }
        if (productDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category with ID " + productDto.getCategoryId() + " not found"));
            product.setCatagory(category);
        }
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> getByName(String name) {
        List<Product> productList = productRepository.findByNameContainingIgnoreCase(name);
        List<ProductDto> productDtoList = productList.stream()
                .map(product -> ProductToProductDto.convertToProduct(product))
                .collect(Collectors.toList());
        return productDtoList;
    }
}
