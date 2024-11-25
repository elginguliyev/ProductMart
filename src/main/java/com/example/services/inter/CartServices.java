package com.example.services.inter;


import com.example.response.CartResponse;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public interface CartServices {

    CartResponse getCart(Principal principal);
}
