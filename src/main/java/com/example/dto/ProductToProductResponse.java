package com.example.dto;

import com.example.response.ImageResponse;
import com.example.entites.Product;
import com.example.response.ProductResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ProductToProductResponse {

    public static ProductResponse convertToProduct(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setQuantity(product.getQuantity());
        productResponse.setCategoryId(product.getCatagory().getId());
        List<ImageResponse> imageResponses = product.getImages().stream()
                .map(image -> new ImageResponse(image.getImageURL()))
                .collect(Collectors.toList());

        productResponse.setImageURLs(imageResponses);
        return productResponse;
    }
}
