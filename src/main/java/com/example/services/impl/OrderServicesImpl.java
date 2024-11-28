package com.example.services.impl;

import com.example.entites.*;
import com.example.exception.MyException;
import com.example.repository.CartRepository;
import com.example.repository.OrderRepository;
import com.example.repository.UserRepository;
import com.example.services.inter.OrderServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServicesImpl implements OrderServices {

    private final UserRepository userRepository;

    private final CartRepository cartRepository;

    private final OrderRepository orderRepository;


    @Override
    public Long createOrder(Principal principal) {

        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new MyException("Istidadəçi tapılmadı", null));

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
            orderItem.setPrice(cartItem.getProduct().getPrice());
            orderItem.setTotalPrice(cartItem.getTotalPrice());
            orderItem.setOrder(order);
            orderItemList.add(orderItem);
        }
        order.setOrderItemList(orderItemList);

        orderRepository.save(order);

        cart.getCartItems().clear();
        cart.setTotalAmount(0.0);
        cartRepository.save(cart);
        return order.getId();

    }
}
