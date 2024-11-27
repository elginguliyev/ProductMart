package com.example.services.impl;

import com.example.entites.*;
import com.example.exception.NotFoundException;
import com.example.repository.CartRepository;
import com.example.repository.OrderRepository;
import com.example.repository.UserRepository;
import com.example.response.OrderResponse;
import com.example.services.inter.OrderServices;
import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class OrderServicesImpl implements OrderServices {

    private final UserRepository userRepository;

    private final CartRepository cartRepository;

    private final OrderRepository orderRepository;


    @Override
    public Long createOrder(Principal principal) {

        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new NotFoundException("Istidadəçi tapılmadı"));

        Cart cart = cartRepository.findByUser(user);

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrdeStatus.GOZLEMEDE);
        order.setTotalAmount(cart.getTotalAmount());

        List<OrderItem> orderItemList = new ArrayList<>();

        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());
            orderItemList.add(orderItem);
        }
        order.setOrderItemList(orderItemList);

        orderRepository.save(order);

        cart.getCartItems().clear();
        cartRepository.save(cart);
        return order.getId();

    }
}
