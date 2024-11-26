package com.example.response;

import lombok.Data;

@Data
public class OrderItemResponse {
    private Long id;
    private ProductResponse product;
    private int quantity;
    private double totalPrice;
}
