package com.example.services.impl;

import com.example.dto.CartItemDto;
import com.example.entites.Cart;
import com.example.entites.CartItem;
import com.example.repository.CartItemRepository;
import com.example.repository.CartRepository;
import com.example.services.CartItemServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemServicesImpl implements CartItemServices {


    private final CartItemRepository cartItemRepository;

    private final CartRepository cartRepository;


    @Override
    public CartItem createCartItem(CartItemDto cartItemDto) {

        Cart cart = cartRepository.findById(cartItemDto.getCartId())
                .orElseThrow(() -> new RuntimeException("Cart not fount"));

        CartItem cartItem = new CartItem();
        cartItem.setProductId(cartItemDto.getProductId());
        cartItem.setPrice(cartItemDto.getPrice());
        cartItem.setQuantity(cartItemDto.getQuantity());
        cartItem.setCart(cart);

        double newAmount = cartItemDto.getPrice() * cartItemDto.getQuantity();

        cart.setTotalAmount(cart.getTotalAmount() + newAmount);


        cart.getCartItems().add(cartItem);
        cartRepository.save(cart);
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
