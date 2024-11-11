package com.example.services;

import com.example.dto.CartDto;
import com.example.dto.CartItemDto;
import com.example.entites.Cart;
import com.example.entites.CartItem;

import java.util.List;

public interface CartServices {

    Cart createCart(CartItemDto cartItemDto);

    CartDto getCartById(Long id);

    List<CartDto> getAllCartss();





}
