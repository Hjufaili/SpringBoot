package com.codeline.demo.controllers;

import com.codeline.demo.dto.*;
import com.codeline.demo.entity.PhoneNumber;
import com.codeline.demo.helper.Constants;
import com.codeline.demo.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/phone")
public class PhoneNumberController {

    @Autowired
    PhoneNumberService phoneNumberService;

    @PostMapping("/phoneNumbers/add")
    public ResponseEntity<PhoneNumberCreateResponse> addPhoneNumberToStudent(
            @RequestBody PhoneNumberCreateRequest request) throws Exception {
        PhoneNumberCreateRequest.validCreatePhoneNumberRequest(request);
        PhoneNumberCreateResponse response = phoneNumberService.addPhoneNumber(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/update/{number}")
    public ResponseEntity<?> updatePhoneNumber (
            @PathVariable String number,
            @RequestBody PhoneNumberCreateRequest request) throws Exception {
        PhoneNumberCreateRequest.convertToPhoneNumber(request);
        PhoneNumber phoneNumber= PhoneNumberCreateRequest.convertToPhoneNumber(request);
        PhoneNumberCreateResponse response = phoneNumberService.updatePhoneNumber(number,phoneNumber);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePhoneNumber (@PathVariable String number) throws Exception {

        phoneNumberService.deletePhoneNumber(number);

        return new ResponseEntity<>("DELETE "+ Constants.SUCCESS,HttpStatus.OK);
    }




}
