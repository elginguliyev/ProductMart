package com.example.controller;

import com.example.request.CartDto;
import com.example.request.CartItemDto;
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
    public ResponseEntity<CartDto> addCartItem(@RequestBody CartItemDto cartItemDto,
                                               @PathVariable Long userId) {
        CartDto cartDto = cartItemServices.createCartItem(cartItemDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartDto);
    }

    @DeleteMapping(path = "user/{userId}/cart/cart-item/{cartItemId}")
    public ResponseEntity<CartDto> removeCartItem(@PathVariable Long userId,
                                                  @PathVariable Long cartItemId) {
        CartDto cartDto = cartItemServices.deleteCartItem(userId, cartItemId);

        return ResponseEntity.ok(cartDto);
    }

    @GetMapping(path = "user/{userId}/cart/cart-items")
    public ResponseEntity<List<CartItemDto>> getAllCartItem(@PathVariable Long userId) {
        List<CartItemDto> cartItemDtos = cartItemServices.getAllCartItems(userId);
        return ResponseEntity.ok(cartItemDtos);
    }

    @PutMapping(path = "user/{userId}/cart/cart-item/{cartItemId}")
    public ResponseEntity<CartDto> updateCartItem(@PathVariable Long userId,
                                                  @PathVariable Long cartItemId,
                                                  @RequestBody CartItemDto cartItemDto) {
        CartDto cartDto = cartItemServices.updateCartItem(userId, cartItemId, cartItemDto);
        return ResponseEntity.ok(cartDto);
    }

    @GetMapping(path = "user/{userId}/cart/cart-item/{cartItemId}")
    public ResponseEntity<CartItemDto> getByIDCartItem(@PathVariable Long userId,
                                                       @PathVariable Long cartItemId) {
        CartItemDto cartItemDto = cartItemServices.getCartItemById(userId, cartItemId);
        return ResponseEntity.ok(cartItemDto);
    }
}
