package com.example.controller;

import com.example.request.CartItemRequest;
import com.example.response.CartItemResponse;
import com.example.response.CartResponse;
import com.example.services.inter.CartItemServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/")
public class CartItemController {

    private final CartItemServices cartItemServices;

    @PostMapping(path = "user/{userId}/cart/add/cart-item")
    public ResponseEntity<CartResponse> addCartItem(@RequestBody CartItemRequest cartItemRequest,
                                                    @PathVariable Long userId) {
        CartResponse cartResponse = cartItemServices.createCartItem(cartItemRequest, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartResponse);
    }

    @DeleteMapping(path = "user/{userId}/cart/cart-item/{cartItemId}")
    public ResponseEntity<CartResponse> removeCartItem(@PathVariable Long userId,
                                                  @PathVariable Long cartItemId) {
        CartResponse cartResponse = cartItemServices.deleteCartItem(userId, cartItemId);

        return ResponseEntity.ok(cartResponse);
    }

    @GetMapping(path = "user/{userId}/cart/cart-items")
    public ResponseEntity<List<CartItemResponse>> getAllCartItem(@PathVariable Long userId) {
        List<CartItemResponse> cartItemResponses = cartItemServices.getAllCartItems(userId);
        return ResponseEntity.ok(cartItemResponses);
    }

    @PutMapping(path = "user/{userId}/cart/cart-item/{cartItemId}")
    public ResponseEntity<CartResponse> updateCartItem(@PathVariable Long userId,
                                                  @PathVariable Long cartItemId,
                                                  @RequestBody CartItemRequest cartItemRequest) {
        CartResponse cartResponse = cartItemServices.updateCartItem(userId, cartItemId, cartItemRequest);
        return ResponseEntity.ok(cartResponse);
    }

    @GetMapping(path = "user/{userId}/cart/cart-item/{cartItemId}")
    public ResponseEntity<CartItemResponse> getByIDCartItem(@PathVariable Long userId,
                                                       @PathVariable Long cartItemId) {

        CartItemResponse cartItemResponse = cartItemServices.getCartItemById(userId, cartItemId);
        return ResponseEntity.ok(cartItemResponse);
    }
}
