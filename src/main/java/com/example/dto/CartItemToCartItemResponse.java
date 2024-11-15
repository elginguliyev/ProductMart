package com.example.dto;

import com.example.entites.CartItem;
import com.example.response.CartItemResponse;

public class CartItemToCartItemResponse {

    public static CartItemResponse convertToCartItemResp(CartItem cartItem) {
        CartItemResponse cartItemResponse = new CartItemResponse();
        cartItemResponse.setProductId(cartItem.getProductId());
        cartItemResponse.setPrice(cartItem.getPrice());
        cartItemResponse.setQuantity(cartItem.getQuantity());

        return cartItemResponse;
    }
}
