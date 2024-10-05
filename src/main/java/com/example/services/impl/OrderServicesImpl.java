package com.example.services.impl;

import com.example.dto.OrderDto;
import com.example.entites.Order;
import com.example.repository.OrderRepository;
import com.example.services.OrderItemServices;
import com.example.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServicesImpl implements OrderServices {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemServices orderItemServices;

    @Override
    public Order createOrder(OrderDto orderDto) {
        Order order= new Order();
        order.setOrderItems(orderItemServices.getAllOrderItems());
        order.setTotalAmount(4673.00);
        return  null;
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not  found"));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrder(Long id, OrderDto orderDto) {
        return null;
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
