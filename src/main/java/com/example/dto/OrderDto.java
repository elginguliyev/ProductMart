package com.example.dto;

import java.util.List;

public class OrderDto {

    private Long userId;
    private List<OrderItemDto> orderItems;

    public OrderDto() {
    }


    public OrderDto(Long userId, List<OrderItemDto> orderItems) {
        this.userId = userId;
        this.orderItems = orderItems;
    }

    public Long getUserId() {
        return userId;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "userId=" + userId +
                ", orderItems=" + orderItems +
                '}';
    }
}
