package com.example.controller;

import com.example.dto.CartItemDto;
import com.example.services.CartItemServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/")
public class CartItemController {

    private final CartItemServices cartItemServices;
    @PostMapping(path = "add/cart-item")
    public ResponseEntity<String> addCartItem(@RequestBody CartItemDto cartItemDto){
        cartItemServices.createCartItem(cartItemDto);
        return ResponseEntity.ok("CartItem created...");
    }



}
