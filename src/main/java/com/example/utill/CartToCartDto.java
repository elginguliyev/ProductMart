package com.example.utill;

import com.example.dto.CartDto;
import com.example.dto.CartItemDto;
import com.example.entites.Cart;
import com.example.entites.CartItem;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.utill.CartItemToCartItemDto.convertToCartItemDto;

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
