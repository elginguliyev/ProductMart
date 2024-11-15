package com.example.services.inter;


import com.example.response.CartResponse;
import org.springframework.stereotype.Service;

@Service
public interface CartServices {

    CartResponse getCart(Long cartId);
}
