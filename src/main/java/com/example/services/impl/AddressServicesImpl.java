package com.example.services.impl;

import com.example.request.AddressRequest;
import com.example.response.AddressResponse;
import com.example.services.inter.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServicesImpl implements AddressService {
    @Override
    public AddressResponse getAllAddress() {
        return null;
    }

    @Override
    public AddressResponse getById(Long id) {
        return null;
    }

    @Override
    public void addAddress(AddressRequest addressRequest) {

    }

    @Override
    public void updateAddress(AddressRequest addressRequest) {

    }

    @Override
    public void deleteAddress(Long id) {

    }
}
