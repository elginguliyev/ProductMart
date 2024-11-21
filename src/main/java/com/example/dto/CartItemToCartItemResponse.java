package com.example.dto;

import com.example.entites.CartItem;
import com.example.response.CartItemResponse;
import com.example.response.ProductResponse;

public class CartItemToCartItemResponse {

    public static CartItemResponse convertToCartItemResp(CartItem cartItem) {
        CartItemResponse cartItemResponse = new CartItemResponse();


        ProductResponse productResponse = ProductToProductResponse.convertToProduct(cartItem.getProduct());
        ProductResponse response = new ProductResponse();
        response.setName(productResponse.getName());
        response.setPrice(productResponse.getPrice());


        cartItemResponse.setId(cartItem.getId());
        cartItemResponse.setProduct(response);
        cartItemResponse.setTotalPrice(cartItem.getTotalPrice());
        cartItemResponse.setQuantity(cartItem.getQuantity());

        return cartItemResponse;
    }
}
