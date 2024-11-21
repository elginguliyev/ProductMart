package com.example.services.inter;

import com.example.request.AddressRequest;
import com.example.response.AddressResponse;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface AddressService {
    List<AddressResponse> getAllAddress(Principal principal);

    AddressResponse getById(Principal principal, Long id);

    void addAddress(Principal principal, AddressRequest addressRequest);

    void updateAddress(AddressRequest addressRequest);

    void deleteAddress(Principal principal, Long id);
}
