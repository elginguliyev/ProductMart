package com.example.services.impl;

import com.example.request.CartDto;
import com.example.request.CartItemDto;
import com.example.entites.Cart;
import com.example.entites.CartItem;
import com.example.entites.User;
import com.example.repository.CartItemRepository;
import com.example.repository.CartRepository;
import com.example.repository.UserRepository;
import com.example.services.inter.CartItemServices;
import com.example.dto.CartItemToCartItemDto;
import com.example.dto.CartToCartDto;
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
    public CartDto createCartItem(CartItemDto cartItemDto, Long userid) {


        User user = userRepository.findById(userid).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user);

        if (cart == null) {
            cart = new Cart();
            cart.setTotalAmount(0.0);
            cart.setUser(user);
            cartRepository.save(cart);
        }

        CartItem existingCartItem = cart.getCartItems().stream()
                .filter(item -> item.getProductId().equals(cartItemDto.getProductId()))
                .findFirst()
                .orElse(null);

        if (existingCartItem != null) {
            int newQuantity = existingCartItem.getQuantity() + cartItemDto.getQuantity();
            existingCartItem.setQuantity(newQuantity);

            double newAmount = existingCartItem.getPrice() * cartItemDto.getQuantity();
            cart.setTotalAmount(cart.getTotalAmount() + newAmount);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setProductId(cartItemDto.getProductId());
            cartItem.setPrice(cartItemDto.getPrice());
            cartItem.setQuantity(cartItemDto.getQuantity());
            cartItem.setCart(cart);

            double newAmount = cartItemDto.getPrice() * cartItemDto.getQuantity();

            cart.setTotalAmount(cart.getTotalAmount() + newAmount);
            cart.getCartItems().add(cartItem);
        }
        Cart cart1 = cartRepository.save(cart);
        return CartToCartDto.convertToCartDto(cart1);

    }

    @Override
    public CartItemDto getCartItemById(Long userId, Long cartItemId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user);

        CartItem item = cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        return CartItemToCartItemDto.convertToCartItemDto(item);
    }

    @Override
    public List<CartItemDto> getAllCartItems(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user);

        List<CartItem> cartItemList = cart.getCartItems();
        return cartItemList.stream()
                .map(cartItem -> CartItemToCartItemDto.convertToCartItemDto(cartItem))
                .collect(Collectors.toList());
    }

    @Override
    public CartDto updateCartItem(Long userId, Long cartItemId, CartItemDto cartItemDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user);

        CartItem existingCartItem = cart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        double newAmount = existingCartItem.getPrice() * cartItemDto.getQuantity();
        double currentAmount = existingCartItem.getPrice() * existingCartItem.getQuantity();

        if (existingCartItem != null) {
            existingCartItem.setQuantity(cartItemDto.getQuantity());
        }

        if (cartItemDto.getQuantity() > existingCartItem.getQuantity()) {
            cart.setTotalAmount(cart.getTotalAmount() + newAmount);
        } else {
            cart.setTotalAmount(cart.getTotalAmount() + newAmount - currentAmount);
        }


        Cart updateCart = cartRepository.save(cart);
        return CartToCartDto.convertToCartDto(updateCart);
    }

    @Override
    public CartDto deleteCartItem(Long useriId, Long cartItemId) {
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
        return CartToCartDto.convertToCartDto(updateCart);
    }
}