package com.example.response;

import com.example.entites.Product;
import lombok.Data;

@Data
public class CartItemResponse {
    private Long id;
    private ProductResponse product;
    private int quantity;
    private double totalPrice;


}

