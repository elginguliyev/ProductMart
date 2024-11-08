package com.example.dto;

import java.util.List;

public class CartDto {

    private Long id;
    private Double totalAmount;
    private List<CartItemDto> cartItems;

    // Constructorlar
    public CartDto() {}

    public CartDto(Long id, Double totalAmount, List<CartItemDto> cartItems) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.cartItems = cartItems;
    }

    // Getters və Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<CartItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }
}
