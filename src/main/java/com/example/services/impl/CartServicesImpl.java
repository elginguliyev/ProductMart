package com.example.services.impl;

import com.example.dto.CartDto;
import com.example.dto.CartItemDto;
import com.example.entites.Cart;
import com.example.entites.CartItem;
import com.example.repository.CartItemRepository;
import com.example.repository.CartRepository;
import com.example.services.CartServices;
import com.example.utill.CartToCartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        cart.setTotalAmount(cartItemDto.getPrice() * cartItemDto.getQuantity());
        cart.getCartItems().add(cartItem);

        Cart savedCart = cartRepository.save(cart);
        cartItemRepository.save(cartItem);


        return savedCart;
    }

    @Override
    public CartDto getCartById(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not Found"));
        return CartToCartDto.convertToCartDto(cart);
    }

    @Override
    public List<CartDto> getAllCartss() {
        List<Cart> carts = cartRepository.findAll();
        return carts.stream()
                .map(cart -> CartToCartDto.convertToCartDto(cart))
                .collect(Collectors.toList());
    }

    @Override
    public void updateCart(Long id, CartItemDto cartItemDto) {

    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}
