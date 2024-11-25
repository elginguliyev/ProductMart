package com.example.services.impl;

import com.example.dto.CartToCartResponse;
import com.example.entites.Cart;
import com.example.entites.User;
import com.example.exception.NotFoundException;
import com.example.repository.CartRepository;
import com.example.repository.UserRepository;
import com.example.response.CartResponse;
import com.example.services.inter.CartServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class CartServicesImpl implements CartServices {


    private final CartRepository cartRepository;

    private final UserRepository userRepository;
    @Override
    public CartResponse getCart(Principal principal) {
           User user=  userRepository.findByUsername(principal.getName())
                   .orElseThrow(() -> new NotFoundException("Istifadəçi tapılmadı"));

        Cart cart = cartRepository.findByUser(user);
        CartResponse cartResponse = CartToCartResponse.convertToCartResp(cart);

        return cartResponse;
    }
}
