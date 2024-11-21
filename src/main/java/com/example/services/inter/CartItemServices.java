package com.example.services.inter;

import com.example.request.CartItemRequest;
import com.example.response.CartItemResponse;
import com.example.response.CartResponse;

import java.security.Principal;
import java.util.List;

public interface CartItemServices {
    CartResponse createCartItem(CartItemRequest cartItemRequest, Principal principal);
    CartItemResponse getCartItemById(Principal principal, Long cartItemId);
    List<CartItemResponse> getAllCartItems(Principal principal);
    CartResponse updateCartItem(Principal principal, Long id, CartItemRequest cartItemRequest);
    CartResponse deleteCartItem(Principal principal, Long cartItemId);

}
