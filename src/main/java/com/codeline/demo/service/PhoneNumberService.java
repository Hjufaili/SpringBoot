package com.codeline.demo.service;

import com.codeline.demo.dto.PhoneNumberCreateRequest;
import com.codeline.demo.dto.PhoneNumberCreateResponse;
import com.codeline.demo.dto.StudentCreateResponse;
import com.codeline.demo.entity.PhoneNumber;
import com.codeline.demo.entity.Student;
import com.codeline.demo.helper.Constants;
import com.codeline.demo.helper.HelperUtils;
import com.codeline.demo.repositories.PhoneNumberRepository;
import com.codeline.demo.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class PhoneNumberService {
    @Autowired
    PhoneNumberRepository phoneNumberRepository;

    @Autowired
    StudentRepository studentRepository;


    public PhoneNumberCreateResponse addPhoneNumber(Integer studentId, PhoneNumberCreateRequest request) throws Exception {
        PhoneNumber phoneNumber = PhoneNumberCreateRequest.convertToPhoneNumber(request);
        phoneNumber.setIsActive(Boolean.TRUE);

        Student student = studentRepository.findStudentById(studentId);
        if (HelperUtils.isNotNull(student)) {
            student = studentRepository.save(student);
        } else {
            throw new Exception(Constants.PHONE_NUMBER_CREATE_REQUEST_STUDENT_ID_NOT_VALID);
        }

        List<PhoneNumber> phoneNumberList = new ArrayList<>();
        phoneNumberList.add(phoneNumber);
        student.setPhoneNumbers(phoneNumberList);
        phoneNumber.setStudent(student);

        return PhoneNumberCreateResponse.convertToPhoneNumber(phoneNumberRepository.save(phoneNumber));

    }

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
}
