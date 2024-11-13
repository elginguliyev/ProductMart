package com.example.request;

import lombok.Data;

@Data
public class CartItemDto {
    private Long id;
    private Long productId;
    private int quantity;
    private double price;


}

