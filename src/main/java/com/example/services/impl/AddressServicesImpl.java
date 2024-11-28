package com.example.services.impl;

import com.example.dto.AddressToAddressResponse;
import com.example.entites.Address;
import com.example.entites.User;
import com.example.exception.MyException;
import com.example.repository.AddressRepository;
import com.example.repository.UserRepository;
import com.example.request.AddressRequest;
import com.example.response.AddressResponse;
import com.example.services.inter.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServicesImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final UserRepository userRepository;

    @Override
    public List<AddressResponse> getAllAddress(Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new MyException("İstifadəçi tapılmadı",null));

        List<Address> addresses = addressRepository.findByUser(user);

        return addresses.stream()
                .map(address -> AddressToAddressResponse.toDTO(address))
                .collect(Collectors.toList());
    }

    @Override
    public AddressResponse getById(Principal principal, Long id) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new MyException("İstifadəçi tapılmadı",null));

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new MyException("Ünvan məlumatları  tapılmadı",null));

        if (address.getUser().equals(user)) {
            throw new MyException("Address bu istifadəçiyə aid deyil",null);
        }
        return AddressToAddressResponse.toDTO(address);
    }


    @Override
    public void addAddress(Principal principal, AddressRequest addressRequest) {
        String userName = principal.getName();

        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new MyException("İstifadəçi tapılmadı",null));

        Address address = new Address();
        address.setCity(addressRequest.getCity());
        address.setStreet(addressRequest.getStreet());
        address.setPhoneNumber(addressRequest.getPhoneNumber());
        address.setUser(user);

        addressRepository.save(address);
    }

    @Override
    public void updateAddress(Principal principal, Long id, AddressRequest addressRequest) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new MyException("İstifadəçi tapılmadı",null));

        Address address = addressRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new MyException("Ünvan məlumatları  tapılmadı",null));

        address.setCity(addressRequest.getCity());
        address.setStreet(addressRequest.getStreet());
        address.setPhoneNumber(addressRequest.getPhoneNumber());

        addressRepository.save(address);
    }

    @Override
    public void deleteAddress(Principal principal, Long id) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new MyException("İstifadəçi tapılmadı",null));
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new MyException("Ünvan məlumatları  tapılmadı",null));

        if (!address.getUser().equals(user)) {
            throw new MyException("Yanliz sizə aid olan ünvanı sile bilərsiz ",null);
        }
        addressRepository.delete(address);
    }
}
