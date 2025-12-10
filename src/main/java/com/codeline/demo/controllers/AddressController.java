package com.codeline.demo.controllers;

import com.codeline.demo.dto.AddressCreateRequest;
import com.codeline.demo.dto.AddressCreateResponse;
import com.codeline.demo.entity.Address;
import com.codeline.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("/create")
    public ResponseEntity<AddressCreateResponse> createAddress(
            @RequestBody AddressCreateRequest request) throws Exception {
            AddressCreateRequest.validCreateAddressRequest(request);

            AddressCreateResponse response = addressService.createAddress(request);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
