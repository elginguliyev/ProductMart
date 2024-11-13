package com.example.controller;

import com.example.request.CartDto;
import com.example.request.CartItemDto;
import com.example.services.inter.CartServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/")
public class CartController {

    private final CartServices cartServices;

    public CartController(CartServices cartServices) {
        this.cartServices = cartServices;
    }

    @GetMapping(path = "carts")
    public List<CartDto> getAllCarts() {
        return cartServices.getAllCartss();
    }

    @PostMapping(path = "add/cart")
    public ResponseEntity<String> addCart(@RequestBody CartItemDto cartItemDto){
        cartServices.createCart(cartItemDto);
        return ResponseEntity.ok("Cart created ");
    }
}
