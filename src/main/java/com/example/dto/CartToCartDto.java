package com.example.dto;

import com.example.request.CartDto;
import com.example.request.CartItemDto;
import com.example.entites.Cart;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.dto.CartItemToCartItemDto.convertToCartItemDto;

public class CartToCartDto {

    public static CartDto convertToCartDto(Cart cart){
        CartDto cartDto=new CartDto();

        List<CartItemDto> cartItemList = cart.getCartItems().stream()
                .map(cartItem -> convertToCartItemDto(cartItem))  // CartItem-ı CartItemDto-ya çeviririk
                .collect(Collectors.toList());
        cartDto.setId(cart.getId());
        cartDto.setUserId(cart.getUser().getId());
        cartDto.setTotalAmount(cart.getTotalAmount());
        cartDto.setCartItems(cartItemList);

        return cartDto;
    }
}
