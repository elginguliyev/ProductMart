package com.example.controller;

import com.example.response.CartResponse;
import com.example.services.inter.CartServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/v1/")
public class CartController {

    private final CartServices cartServices;

    public CartController(CartServices cartServices) {
        this.cartServices = cartServices;
    }

    @GetMapping(path = "cart/{cartId}")
    public ResponseEntity<CartResponse> getCarts(@RequestParam Long cartId) {
        CartResponse cartResponse = cartServices.getCart(cartId);
        return ResponseEntity.ok(cartResponse);
    }

}
