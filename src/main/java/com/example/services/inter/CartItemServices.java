package com.example.services.inter;

import com.example.dto.CartDto;
import com.example.dto.CartItemDto;

import java.util.List;

public interface CartItemServices {
    CartDto createCartItem(CartItemDto cartItemDto, Long userId);
    CartItemDto getCartItemById(Long userId, Long cartItemId);
    List<CartItemDto> getAllCartItems(Long userId);
    CartDto updateCartItem(Long userId, Long id, CartItemDto orderItemDto);
    CartDto deleteCartItem(Long userId, Long cartItemId);

}
