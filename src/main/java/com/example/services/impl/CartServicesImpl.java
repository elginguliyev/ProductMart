package com.example.services.impl;

import com.example.dto.CartItemDto;
import com.example.entites.Cart;
import com.example.entites.CartItem;
import com.example.repository.CartItemRepository;
import com.example.repository.CartRepository;
import com.example.services.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServicesImpl implements CartServices {


    private CartRepository cartRepository;


    private CartItemRepository cartItemRepository;

    public CartServicesImpl(CartRepository cartRepository,
                            CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Cart createCart(CartItemDto cartItemDto) {

        Cart cart = new Cart();
        CartItem cartItem = new CartItem();
        cartItem.setProductId(cartItemDto.getProductId());
        cartItem.setPrice(cartItemDto.getPrice());
        cartItem.setQuantity(cartItemDto.getQuantity());
        cartItem.setCart(cart);

        cart.setTotalAmount(cartItemDto.getPrice() * cartItemDto.getProductId());
        cart.getCartItems().add(cartItem);

        Cart savedCart = cartRepository.save(cart);
        cartItemRepository.save(cartItem);


        return savedCart;
    }

    @Override
    public Cart getCartById(Long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        return cart.orElseThrow(() -> new RuntimeException("Cart not Found"));
    }

    @Override
    public List<Cart> getAllCartss() {
        return cartRepository.findAll();
    }

    @Override
    public Cart updateCart(Long id, CartItemDto cartItemDto) {
        return null;
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}
