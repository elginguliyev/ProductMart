package com.example.services.impl;

import com.example.dto.OrderItemDto;
import com.example.entites.OrderItem;
import com.example.services.OrderItemServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServicesImpl implements OrderItemServices {







    @Override
    public OrderItem createOrderItem(OrderItemDto orderItemDto) {
        return null;
    }

    @Override
    public OrderItem getOrderItemById(Long id) {
        return null;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return null;
    }

    @Override
    public OrderItem updateOrderItem(Long id, OrderItemDto orderItemDto) {
        return null;
    }

    @Override
    public void deleteOrderItem(Long id) {

    }
}
