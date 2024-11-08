package com.example.services.impl;

import com.example.dto.CartItemDto;
import com.example.entites.CartItem;
import com.example.repository.CartItemRepository;
import com.example.services.CartItemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServicesImpl implements CartItemServices {


    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItem createCartItem(CartItemDto cartItemDto) {
        CartItem cartItem = new CartItem();
        cartItem.setProductId(cartItemDto.getProductId());
        cartItem.setPrice(cartItemDto.getPrice());
        cartItem.setQuantity(cartItemDto.getQuantity());

        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem getCartItemById(Long id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("cartItem can not find"));
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem updateCartItem(Long id, CartItemDto orderItemDto) {
        return null;
    }

    @Override
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }
}
