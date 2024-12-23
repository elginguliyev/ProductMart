package com.example.controller;

import com.example.request.AddressRequest;
import com.example.response.AddressResponse;
import com.example.services.inter.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/")
@CrossOrigin("*")
public class AddressController {

    private final AddressService addressService;

    @PostMapping(path = "add-address")
    public ResponseEntity<String> addAddress(Principal principal,
                                             @RequestBody AddressRequest addressRequest) {
        System.out.println(addressRequest.getCity());
        addressService.addAddress(principal, addressRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Address add successfuly");
    }

    @DeleteMapping(path = "address/{id}")
    public ResponseEntity<String> deleteAddress(Principal principal, @PathVariable Long id) {
        addressService.deleteAddress(principal, id);
        return ResponseEntity.ok("Address delete successfully");
    }

    @GetMapping(path = "addresses")
    public ResponseEntity<List<AddressResponse>> getAllAddress(Principal principal) {
        List<AddressResponse> addressResponses = addressService.getAllAddress(principal);
        return ResponseEntity.ok(addressResponses);
    }

    @GetMapping(path = "address/{id}")
    public ResponseEntity<AddressResponse> getById(Principal principal, @PathVariable Long id) {
        AddressResponse addressResponse = addressService.getById(principal, id);
        return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
    }

    @PatchMapping(path = "address/{id}")
    public ResponseEntity<String> updateAddress(Principal principal,
                                                @PathVariable Long id,
                                                @RequestBody AddressRequest addressRequest) {

        addressService.updateAddress(principal, id, addressRequest);

        return ResponseEntity.status(HttpStatus.OK).body("Address update successfully");

    }

}
