package com.example.services;

import com.example.entites.Order;

import java.util.List;

public interface OrderServices {

    Order createOrder(OrderDto orderDto);

    Order getOrderById(Long id);

    List<Order> getAllOrders();

    Order updateOrder(Long id, OrderDto orderDto);

    void deleteOrder(Long id);

}
