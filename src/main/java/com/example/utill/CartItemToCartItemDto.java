package com.example.utill;

import com.example.dto.CartItemDto;
import com.example.entites.CartItem;

public class CartItemToCartItemDto {

    public static CartItemDto convertToCartItemDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();

        cartItemDto.setProductId(cartItem.getProductId());
        cartItemDto.setPrice(cartItem.getPrice());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setCartId(cartItem.getCart().getId());
        // İstəyə bağlı digər sahələr varsa, onları da burada əlavə edə bilərsiniz
        return cartItemDto;
    }
}
