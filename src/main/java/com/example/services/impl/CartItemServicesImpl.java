package com.example.services.impl;

import com.example.entites.Cart;
import com.example.entites.CartItem;
import com.example.entites.User;
import com.example.repository.CartItemRepository;
import com.example.repository.CartRepository;
import com.example.repository.UserRepository;
import com.example.request.CartItemRequest;
import com.example.response.CartItemResponse;
import com.example.response.CartResponse;
import com.example.services.inter.CartItemServices;
import com.example.dto.CartItemToCartItemResponse;
import com.example.dto.CartToCartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemServicesImpl implements CartItemServices {


    private final CartItemRepository cartItemRepository;

    private final CartRepository cartRepository;

    private final UserRepository userRepository;

    @Override
    public CartResponse createCartItem(CartItemRequest cartItemRequest, Long userid) {


        User user = userRepository.findById(userid).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user);

        if (cart == null) {
            cart = new Cart();
            cart.setTotalAmount(0.0);
            cart.setUser(user);
            cartRepository.save(cart);
        }

        CartItem existingCartItem = cart.getCartItems().stream()
                .filter(item -> item.getProductId().equals(cartItemRequest.getProductId()))
                .findFirst()
                .orElse(null);

        if (existingCartItem != null) {
            int newQuantity = existingCartItem.getQuantity() + cartItemRequest.getQuantity();
            existingCartItem.setQuantity(newQuantity);

            double newAmount = existingCartItem.getPrice() * cartItemRequest.getQuantity();
            cart.setTotalAmount(cart.getTotalAmount() + newAmount);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setProductId(cartItemRequest.getProductId());
            cartItem.setPrice(cartItemRequest.getPrice());
            cartItem.setQuantity(cartItemRequest.getQuantity());
            cartItem.setCart(cart);

            double newAmount = cartItemRequest.getPrice() * cartItemRequest.getQuantity();

            cart.setTotalAmount(cart.getTotalAmount() + newAmount);
            cart.getCartItems().add(cartItem);
        }
        Cart cart1 = cartRepository.save(cart);
        return CartToCartResponse.convertToCartResp(cart1);

    }

    @Override
    public CartItemResponse getCartItemById(Long userId, Long cartItemId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user);

        CartItem item = cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        return CartItemToCartItemResponse.convertToCartItemResp(item);
    }

    @Override
    public List<CartItemResponse> getAllCartItems(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user);

        List<CartItem> cartItemList = cart.getCartItems();
        return cartItemList.stream()
                .map(cartItem -> CartItemToCartItemResponse.convertToCartItemResp(cartItem))
                .collect(Collectors.toList());
    }

    @Override
    public CartResponse updateCartItem(Long userId, Long cartItemId, CartItemRequest cartItemRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user);

        CartItem existingCartItem = cart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        double newAmount = existingCartItem.getPrice() * cartItemRequest.getQuantity();
        double currentAmount = existingCartItem.getPrice() * existingCartItem.getQuantity();

        if (existingCartItem != null) {
            existingCartItem.setQuantity(cartItemRequest.getQuantity());
        }

        if (cartItemRequest.getQuantity() > existingCartItem.getQuantity()) {
            cart.setTotalAmount(cart.getTotalAmount() + newAmount);
        } else {
            cart.setTotalAmount(cart.getTotalAmount() + newAmount - currentAmount);
        }


        Cart updateCart = cartRepository.save(cart);
        return CartToCartResponse.convertToCartResp(updateCart);
    }

    @Override
    public CartResponse deleteCartItem(Long useriId, Long cartItemId) {
        User user = userRepository.findById(useriId).orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            throw new RuntimeException("cart not found");
        }


        CartItem exsistingcartItem = cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getId().equals(cartItemId))
                .findFirst()
                .orElse(null);
        double newAmount = exsistingcartItem.getPrice() * exsistingcartItem.getQuantity();
        cart.setTotalAmount(cart.getTotalAmount() - newAmount);

        cart.getCartItems().remove(exsistingcartItem);
        cartItemRepository.deleteById(cartItemId);

        Cart updateCart = cartRepository.save(cart);
        return CartToCartResponse.convertToCartResp(updateCart);
    }
}