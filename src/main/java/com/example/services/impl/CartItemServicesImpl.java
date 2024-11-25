package com.example.services.impl;

import com.example.entites.Cart;
import com.example.entites.CartItem;
import com.example.entites.Product;
import com.example.entites.User;
import com.example.exception.NotFoundException;
import com.example.repository.CartItemRepository;
import com.example.repository.CartRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import com.example.request.CartItemRequest;
import com.example.response.CartItemResponse;
import com.example.response.CartResponse;
import com.example.services.inter.CartItemServices;
import com.example.dto.CartItemToCartItemResponse;
import com.example.dto.CartToCartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemServicesImpl implements CartItemServices {


    private final CartItemRepository cartItemRepository;

    private final CartRepository cartRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    @Override
    public CartResponse createCartItem(CartItemRequest cartItemRequest, Principal principal) {

        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("İstifadəçi tapılmadı"));

        Product product = productRepository.findById(cartItemRequest.getProductId())
                .orElseThrow(() -> new NotFoundException("Məhsul tapılmadı"));

        Cart cart = cartRepository.findByUser(user);

        if (cart == null) {
            cart = new Cart();
            cart.setTotalAmount(0.0);
            cart.setUser(user);
            cartRepository.save(cart);
        }

        CartItem existingCartItem = cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(cartItemRequest.getProductId()))
                .findFirst()
                .orElse(null);

        if (existingCartItem != null) {
            Integer newQuantity = existingCartItem.getQuantity() + cartItemRequest.getQuantity();
            existingCartItem.setQuantity(newQuantity);
            existingCartItem.setTotalPrice(product.getPrice() * newQuantity);

            double newAmount = existingCartItem.getProduct().getPrice() * cartItemRequest.getQuantity();
            cart.setTotalAmount(cart.getTotalAmount() + newAmount);
        } else {
            CartItem cartItem = new CartItem();
            double totalPrice = product.getPrice() * cartItemRequest.getQuantity();
            cartItem.setProduct(product);
            cartItem.setTotalPrice(totalPrice);
            cartItem.setQuantity(cartItemRequest.getQuantity());
            cartItem.setCart(cart);

            ;

            cart.setTotalAmount(cart.getTotalAmount() + totalPrice);
            cart.getCartItems().add(cartItem);
        }

        Cart createdCart = cartRepository.save(cart);
        return CartToCartResponse.convertToCartResp(createdCart);

    }

    @Override
    public CartItemResponse getCartItemById(Principal principal, Long cartItemId) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("İstifadəçi tapılmadı"));

        Cart cart = cartRepository.findByUser(user);

        CartItem item = cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Səbət məhsulu tapılmadı"));

        return CartItemToCartItemResponse.convertToCartItemResp(item);
    }

    @Override
    public List<CartItemResponse> getAllCartItems(Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("İstifadəçi tapılmadı"));

        Cart cart = cartRepository.findByUser(user);

        List<CartItem> cartItemList = cart.getCartItems();
        return cartItemList.stream()
                .map(cartItem -> CartItemToCartItemResponse.convertToCartItemResp(cartItem))
                .collect(Collectors.toList());
    }

    @Override
    public CartResponse updateCartItem(Principal principal, Long cartItemId, CartItemRequest cartItemRequest) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("İstifadəçi tapılmadı"));


        Cart cart = cartRepository.findByUser(user);

        CartItem existingCartItem = cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Səbət məhsulu tapılmadı"));

        double newAmount = existingCartItem.getProduct().getPrice() * cartItemRequest.getQuantity();
        double currentAmount = existingCartItem.getProduct().getPrice() * existingCartItem.getQuantity();

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
    public CartResponse deleteCartItem(Principal principal, Long cartItemId) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("İstifadəçi tapılmadı"));

        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            throw new NotFoundException("Səbət tapılmadı");
        }


        CartItem exsistingcartItem = cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getId().equals(cartItemId))
                .findFirst()
                .orElse(null);
        double newAmount = exsistingcartItem.getProduct().getPrice() * exsistingcartItem.getQuantity();
        cart.setTotalAmount(cart.getTotalAmount() - newAmount);


        cart.getCartItems().remove(exsistingcartItem);
        cartItemRepository.deleteById(exsistingcartItem.getId());


        Cart updateCart = cartRepository.save(cart);
        return CartToCartResponse.convertToCartResp(updateCart);
    }
}