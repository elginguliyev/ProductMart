package com.example.utill;

import com.example.dto.CartItemDto;
import com.example.entites.CartItem;

public class CartItemToCartItemDto {

    public static CartItemDto convertToCartItemDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
         cartItemDto.setId(cartItem.getId());
        cartItemDto.setProductId(cartItem.getProductId());
        cartItemDto.setPrice(cartItem.getPrice());
        cartItemDto.setQuantity(cartItem.getQuantity());

        return cartItemDto;
    }
}
