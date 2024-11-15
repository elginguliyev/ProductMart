package com.example.response;

import lombok.Data;

import java.util.List;

@Data
public class CartResponse {

    private Long userId;
    private Double totalAmount;
    private List<CartItemResponse> cartItems;


}
