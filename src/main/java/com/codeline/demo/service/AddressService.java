package com.codeline.demo.service;

import com.codeline.demo.dto.AddressCreateRequest;
import com.codeline.demo.dto.AddressCreateResponse;
import com.codeline.demo.dto.StudentCreateResponse;
import com.codeline.demo.entity.Address;
import com.codeline.demo.entity.Student;
import com.codeline.demo.helper.HelperUtils;
import com.codeline.demo.repositories.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AddressService {

    @Autowired
    AddressRepository addressRepository;


    public AddressCreateResponse createAddress(AddressCreateRequest request) throws Exception {

        Address address = AddressCreateRequest.convertToAddress(request);
        address.setIsActive(Boolean.TRUE);

        return AddressCreateResponse.convertToAddress(addressRepository.save(address));
    }

    public AddressCreateResponse updateAddress(Integer id, Address address) throws Exception {
        Address existingAddress = addressRepository.findAddressById(id);
        if (HelperUtils.isNotNull(existingAddress)) {
            address.setCreatedDate(existingAddress.getCreatedDate());
            address.setIsActive(existingAddress.getIsActive());
            return AddressCreateResponse.convertToAddress(addressRepository.save(address));
        } else {
            throw new Exception("BAD REQUEST");
        }
    }
}
