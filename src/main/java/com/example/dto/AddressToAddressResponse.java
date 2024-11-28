package com.example.dto;

import com.example.entites.Address;
import com.example.response.AddressResponse;


public class AddressToAddressResponse {


    public static AddressResponse toDTO(Address address) {
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setId(address.getId());
        addressResponse.setCity(address.getCity());
        addressResponse.setStreet(address.getStreet());
        addressResponse.setPhoneNumber(address.getPhoneNumber());
        return addressResponse;
    }
}
