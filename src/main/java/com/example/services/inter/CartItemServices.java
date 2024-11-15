package com.example.services.inter;

import com.example.request.CartItemRequest;
import com.example.response.CartItemResponse;
import com.example.response.CartResponse;

import java.util.List;

public interface CartItemServices {
    CartResponse createCartItem(CartItemRequest cartItemRequest, Long userId);
    CartItemResponse getCartItemById(Long userId, Long cartItemId);
    List<CartItemResponse> getAllCartItems(Long userId);
    CartResponse updateCartItem(Long userId, Long id, CartItemRequest cartItemRequest);
    CartResponse deleteCartItem(Long userId, Long cartItemId);

}
