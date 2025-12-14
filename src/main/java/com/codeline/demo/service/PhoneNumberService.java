package com.codeline.demo.service;

import com.codeline.demo.dto.PhoneNumberCreateResponse;
import com.codeline.demo.entity.PhoneNumber;
import com.codeline.demo.helper.Constants;
import com.codeline.demo.helper.HelperUtils;
import com.codeline.demo.repositories.PhoneNumberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PhoneNumberService {
    @Autowired
    PhoneNumberRepository phoneNumberRepository;


    public PhoneNumberCreateResponse updatePhoneNumber(String phoneNumber, PhoneNumber request) throws Exception {
        PhoneNumber existingPhoneNumber = phoneNumberRepository.findPhoneNumberByNumber(phoneNumber);

        if (HelperUtils.isNotNull(existingPhoneNumber)) {

            return PhoneNumberCreateResponse.convertToPhoneNumber(phoneNumberRepository.save(request));
        }
        throw new Exception(Constants.BAD_REQUEST);
    }

    public void deletePhoneNumber(String number) throws Exception {
        PhoneNumber existingPhoneNumber = phoneNumberRepository.findPhoneNumberByNumber(number);
        if (HelperUtils.isNotNull(existingPhoneNumber)) {
            existingPhoneNumber.setIsActive(Boolean.FALSE);
            phoneNumberRepository.save(existingPhoneNumber);
        } else {
            throw new Exception(Constants.BAD_REQUEST);
        }
    }

    public List<PhoneNumberCreateResponse> getAllPhoneNumber(){
        List<PhoneNumber> phoneNumbers = phoneNumberRepository.findByIsActiveTrue();
        List<PhoneNumberCreateResponse> responsesList= new ArrayList<>();

        for (PhoneNumber response : phoneNumbers){
            PhoneNumberCreateResponse phoneResponse = PhoneNumberCreateResponse.convertToPhoneNumber(response);
            responsesList.add(phoneResponse);
        }
        return responsesList;
    }
}
