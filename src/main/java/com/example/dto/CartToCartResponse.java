package com.example.dto;

import com.example.entites.Cart;
import com.example.response.CartItemResponse;
import com.example.response.CartResponse;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.dto.CartItemToCartItemResponse.convertToCartItemResp;

public class CartToCartResponse {

    public static CartResponse convertToCartResp(Cart cart){
        CartResponse cartResponse=new CartResponse();

        List<CartItemResponse> cartItemList = cart.getCartItems().stream()
                .map(cartItem -> convertToCartItemResp(cartItem))
                .collect(Collectors.toList());
        cartResponse.setUserId(cart.getUser().getId());
        cartResponse.setTotalAmount(cart.getTotalAmount());
        cartResponse.setCartItems(cartItemList);

        return cartResponse;
    }
}
