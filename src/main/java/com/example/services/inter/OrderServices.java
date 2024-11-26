package com.example.services.inter;

import com.example.response.OrderResponse;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public interface OrderServices {

   OrderResponse createOrder(Principal principal);



}
