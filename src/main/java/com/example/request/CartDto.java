package com.example.request;

import lombok.Data;

import java.util.List;

@Data
public class CartDto {

    private Long id;
    private Long userId;
    private Double totalAmount;
    private List<CartItemDto> cartItems;


}
