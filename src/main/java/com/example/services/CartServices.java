package com.example.services;

import com.example.dto.CartItemDto;
import com.example.entites.Cart;
import com.example.entites.CartItem;

import java.util.List;

public interface CartServices {

    Cart createCart(CartItemDto cartItemDto);

    Cart getCartById(Long id);

    List<Cart> getAllCartss();

    Cart updateCart(Long id, CartItemDto cartItemDto);

    void deleteCart(Long id);

}
