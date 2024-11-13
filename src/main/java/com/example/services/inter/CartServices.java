package com.example.services.inter;

import com.example.request.CartDto;
import com.example.request.CartItemDto;
import com.example.entites.Cart;

import java.util.List;

public interface CartServices {

    Cart createCart(CartItemDto cartItemDto);

    CartDto getCartById(Long id);

    List<CartDto> getAllCartss();





}
