package com.example.controller;

import com.example.response.CartResponse;
import com.example.services.inter.CartServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(path = "/api/")
@RequiredArgsConstructor
public class CartController {

    private final CartServices cartServices;

    @GetMapping(path = "cart")
    public ResponseEntity<CartResponse> getCart(Principal principal) {
        CartResponse cartResponse = cartServices.getCart(principal);

        return ResponseEntity.status(HttpStatus.OK).body(cartResponse);
    }
}
