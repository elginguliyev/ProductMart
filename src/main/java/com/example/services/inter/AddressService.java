package com.example.services.inter;

import com.example.request.AddressRequest;
import com.example.response.AddressResponse;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {
     AddressResponse getAllAddress();

     AddressResponse getById(Long id);

     void addAddress(AddressRequest addressRequest);

     void updateAddress(AddressRequest addressRequest);

     void deleteAddress(Long id);
}
