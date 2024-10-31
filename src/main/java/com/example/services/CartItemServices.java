package com.example.services;

import com.example.dto.CartItemDto;
import com.example.entites.CartItem;

import java.util.List;

public interface CartItemServices {
    CartItem createCartItem(CartItemDto cartItemDto);
    CartItem getCartItemById(Long id);
    List<CartItem> getAllCartItems();
    CartItem updateCartItem(Long id, CartItemDto orderItemDto);
    void deleteCartItem(Long id);

}
