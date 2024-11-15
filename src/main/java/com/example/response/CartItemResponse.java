package com.example.response;

import lombok.Data;

@Data
public class CartItemResponse {

    private Long productId;
    private int quantity;
    private double price;


}

