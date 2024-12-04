package com.example.services.impl;

import com.example.exception.MyException;
import com.example.request.ProductRequest;
import com.example.entites.Category;
import com.example.entites.Image;
import com.example.entites.Product;
import com.example.repository.CategoryRepository;
import com.example.repository.ImageRepository;
import com.example.repository.ProductRepository;
import com.example.response.ProductResponse;
import com.example.services.inter.ImageServices;
import com.example.services.inter.ProductsServices;
import com.example.dto.ProductToProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServicesImpl implements ProductsServices {


    private final ProductRepository productRepository;
    private final ImageServices imageServices;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) throws IOException {
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new MyException("Kataqoriya tapılmadı", null));

        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice() != null ? productRequest.getPrice() : 0.0);
        product.setQuantity(productRequest.getQuantity() != null ? productRequest.getQuantity() : 0);
        product.setCategory(category);
        product.setLocation(productRequest.getLocation());

        List<Image> images = new ArrayList<>();

        for (Image file : productRequest.getFiles()) {
            Image image = new Image();
            image.setImageURL(file.getImageURL());
            image.setProduct(product);
            images.add(image);
        }
        product.setImages(images);
        Product addProduct = productRepository.save(product);
        imageRepository.saveAll(images);
        ProductResponse productResponse = ProductToProductResponse.convertToProduct(addProduct);
        return productResponse;
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new MyException("Məhsul tapılmadı", null));

        ProductResponse productResponse = ProductToProductResponse.convertToProduct(product);
        return productResponse;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .map(product -> ProductToProductResponse.convertToProduct(product))
                .collect(Collectors.toList());
    }

    @Override
    public void updateProduct(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new MyException("Məhsul tapılmadı", null));


            product.setName(productRequest.getName());
            product.setDescription(productRequest.getDescription());
            product.setPrice(productRequest.getPrice());

            product.setQuantity(productRequest.getQuantity());
            product.setLocation(productRequest.getLocation());
            Category category = categoryRepository.findById(productRequest.getCategoryId())
                    .orElseThrow(() -> new MyException("Kataqoriya tapılmadı", null));
            product.setCategory(category);


        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponse> getByName(String name) {
        List<Product> productList = productRepository.findByNameContaining(name);
        List<ProductResponse> responseList = productList.stream()
                .map(product -> ProductToProductResponse.convertToProduct(product))
                .collect(Collectors.toList());
        return responseList;
    }

    @Override
    public List<ProductResponse> getByNameAndLocationAndCategory(String name, String location, String categoryName) {
        if ((location == null || location.isEmpty()) && (categoryName == null || categoryName.isEmpty())) {

            List<Product> productList = productRepository.findByNameContaining(name);
            return productList.stream()
                    .map(product -> ProductToProductResponse.convertToProduct(product))
                    .collect(Collectors.toList());
        } else if (location == null|| location.isEmpty()) {

            List<Product> productList = productRepository.findByNameContainingAndCategory_Name(name, categoryName);
            return productList.stream()
                    .map(product -> ProductToProductResponse.convertToProduct(product))
                    .collect(Collectors.toList());
        } else if (categoryName == null|| categoryName.isEmpty()) {

            List<Product> productList = productRepository.findByNameContainingAndLocation(name, location);
            return productList.stream()
                    .map(product -> ProductToProductResponse.convertToProduct(product))
                    .collect(Collectors.toList());
        } else {
            List<Product> productList = productRepository.findByNameContainingAndLocationAndCategory_Name(name, location, categoryName);
            return productList.stream()
                    .map(product -> ProductToProductResponse.convertToProduct(product))
                    .collect(Collectors.toList());
        }

    }


}
