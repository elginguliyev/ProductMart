package com.example.controller;

import com.example.request.CartItemRequest;
import com.example.response.CartItemResponse;
import com.example.response.CartResponse;
import com.example.services.inter.CartItemServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/")
public class CartItemController {

    private final CartItemServices cartItemServices;

    @PostMapping(path = "cart/add-cart-item")
    public ResponseEntity<CartResponse> addCartItem(@RequestBody CartItemRequest cartItemRequest,
                                                    Principal principal) {
        CartResponse cartResponse = cartItemServices.createCartItem(cartItemRequest, principal);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartResponse);
    }

    @DeleteMapping(path = "cart/cart-item/{cartItemId}")
    public ResponseEntity<CartResponse> removeCartItem(Principal principal ,
                                                  @PathVariable Long cartItemId) {
        CartResponse cartResponse = cartItemServices.deleteCartItem(principal, cartItemId);

        return ResponseEntity.ok(cartResponse);
    }

    @GetMapping(path = "cart/cart-items")
    public ResponseEntity<List<CartItemResponse>> getAllCartItem(Principal principal) {
        List<CartItemResponse> cartItemResponses = cartItemServices.getAllCartItems(principal);
        return ResponseEntity.ok(cartItemResponses);
    }

    @PutMapping(path = "cart/cart-item/{cartItemId}")
    public ResponseEntity<CartResponse> updateCartItem(Principal principal,
                                                  @PathVariable Long cartItemId,
                                                  @RequestBody CartItemRequest cartItemRequest) {
        CartResponse cartResponse = cartItemServices.updateCartItem(principal, cartItemId, cartItemRequest);
        return ResponseEntity.ok(cartResponse);
    }

    @GetMapping(path = "cart/cart-item/{cartItemId}")
    public ResponseEntity<CartItemResponse> getByIDCartItem(Principal principal,
                                                       @PathVariable Long cartItemId) {

        CartItemResponse cartItemResponse = cartItemServices.getCartItemById(principal, cartItemId);
        return ResponseEntity.ok(cartItemResponse);
    }
}
