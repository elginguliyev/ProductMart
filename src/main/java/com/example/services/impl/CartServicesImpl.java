package com.example.services.impl;

import com.example.dto.CartToCartResponse;
import com.example.entites.Cart;
import com.example.repository.CartRepository;
import com.example.response.CartResponse;
import com.example.services.inter.CartServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServicesImpl implements CartServices {


    private final CartRepository cartRepository;


    @Override
    public CartResponse getCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartResponse cartResponse = CartToCartResponse.convertToCartResp(cart);

        return cartResponse;
    }
}
