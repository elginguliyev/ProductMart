package com.example.services.impl;

import com.example.dto.OrderDto;
import com.example.entites.Order;
import com.example.services.OrderServices;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServicesImpl implements OrderServices {






    @Override
    public Order createOrder(OrderDto orderDto) {
        return null;
    }

    @Override
    public Order getOrderById(Long id) {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public Order updateOrder(Long id, OrderDto orderDto) {
        return null;
    }

    @Override
    public void deleteOrder(Long id) {

    }
}
