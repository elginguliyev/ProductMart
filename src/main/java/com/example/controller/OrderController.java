package com.example.controller;

import com.example.services.inter.OrderServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(path = "/api/")
public class OrderController {

    private final OrderServices orderServices;

    public OrderController(OrderServices orderServices) {
        this.orderServices = orderServices;
    }

    @PostMapping(path = "add-order")
    public ResponseEntity<Long> createOrder(Principal principal) {

        Long orderId = orderServices.createOrder(principal);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }
}
