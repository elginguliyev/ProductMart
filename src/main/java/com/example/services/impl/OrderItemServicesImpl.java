package com.example.services.impl;

import com.example.dto.OrderItemDto;
import com.example.entites.OrderItem;
import com.example.entites.Product;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.services.OrderItemServices;
import com.example.services.ProductsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServicesImpl implements OrderItemServices {


    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductsServices productsServices;

    @Override
    public OrderItem createOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        Product product = productsServices.getProductById(orderItemDto.getProductId());
        double totalPrice = orderItemDto.getQuantity() * product.getPrice();

        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setProduct(product);
        orderItem.setPrice(totalPrice);
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordet item not found"));
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem updateOrderItem(Long id, OrderItemDto orderItemDto) {
        OrderItem orderItem = getOrderItemById(id);
        Product product = productsServices.getProductById(orderItemDto.getProductId());
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setProduct(product);
        return orderItemRepository.save(orderItem);
    }

    @Override
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}
