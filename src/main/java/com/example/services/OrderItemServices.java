package com.example.services;

import com.example.entites.OrderItem;

import java.util.List;

public interface OrderItemServices {
    OrderItem createOrderItem(OrderItemDto orderItemDto);
    OrderItem getOrderItemById(Long id);
    List<OrderItem> getAllOrderItems();
    OrderItem updateOrderItem(Long id, OrderItemDto orderItemDto);
    void deleteOrderItem(Long id);

}
