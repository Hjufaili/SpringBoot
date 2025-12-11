package com.codeline.demo.service;

import com.codeline.demo.dto.*;
import com.codeline.demo.entity.Address;
import com.codeline.demo.entity.Course;
import com.codeline.demo.entity.PhoneNumber;
import com.codeline.demo.entity.Student;
import com.codeline.demo.helper.Constants;
import com.codeline.demo.helper.HelperUtils;
import com.codeline.demo.repositories.AddressRepository;
import com.codeline.demo.repositories.PhoneNumberRepository;
import com.codeline.demo.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PhoneNumberRepository phoneNumberRepository;

    @Autowired
    AddressRepository addressRepository;


    public StudentCreateResponse addStudent(StudentCreateRequest request) throws Exception {
        Student student = StudentCreateRequest.convertToStudent(request);
        student.setIsActive(Boolean.TRUE);

        List<PhoneNumber> phoneNumberList = new ArrayList<>();

        for (PhoneNumberCreateRequest phoneNumberReq : request.getPhoneNumber()) {
            PhoneNumber phoneNumber;
            phoneNumber = PhoneNumber.builder()
                    .number(phoneNumberReq.getNumber())
                    .countryCode(phoneNumberReq.getCountryCode())
                    .isLandLine(phoneNumberReq.getIsLandLine())
                    .build();
            phoneNumberList.add(phoneNumber);
        }

        student.setPhoneNumbers(phoneNumberList);


        AddressCreateRequest addressReq = request.getAddress();
        Address address;
        address = Address.builder()
                .houseNumber(addressReq.getHouseNumber())
                .city(addressReq.getCity())
                .country(addressReq.getCountry())
                .stateOrProvince(addressReq.getStateOrProvince())
                .postalCode(addressReq.getPostalCode())
                .street(addressReq.getStreet())
                .postalCode(addressReq.getPostalCode())
                .build();

        address.setStudent(student);
        address = addressRepository.save(address);
        student.setAddress(address);


        /*List<PhoneNumber> phoneNumbers = phoneNumberRepository.findPhoneNumberByNumber(request.getPhoneNumbers());
        if (HelperUtils.isNotNull(phoneNumbers) && HelperUtils.isListNotEmpty(phoneNumbers)){
            student.setPhoneNumbers(phoneNumbers);
        }else {
            throw new Exception(Constants.STUDENT_CREATE_REQUEST_PHONE_NUMBER_NOT_VALID);
        }*/

        /*Address address = addressRepository.findAddressById(request.getAddressId());
        if (HelperUtils.isNotNull(address)){
            student.setAddress(address);
        }else {
            throw new Exception(Constants.STUDENT_CREATE_REQUEST_ADDRESS_ID_NOT_VALID);
        }*/

        return StudentCreateResponse.convertToStudent(studentRepository.save(student));
    }

    public List<StudentCreateResponse> getAllStudents() {
        List<Student> students = studentRepository.findByIsActiveTrue();
        List<StudentCreateResponse> result = new ArrayList<>();
        for (Student c : students) {
            result.add(StudentCreateResponse.convertToStudent(c));
        }
        return result;
    }

    public StudentCreateResponse getStudentById(Integer id) throws Exception {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new Exception("Student not found"));
        if (existingStudent.getIsActive()) {
            return StudentCreateResponse.convertToStudent(existingStudent);
        } else {
            throw new Exception("BAD REQUEST");
        }
    }

    public StudentCreateResponse updateStudent(Integer id, Student student) throws Exception {
        Student existingStudent = studentRepository.findStudentById(id);
        if (HelperUtils.isNotNull(existingStudent)) {
            student.setCreatedDate(existingStudent.getCreatedDate());
            student.setIsActive(existingStudent.getIsActive());
            return StudentCreateResponse.convertToStudent(studentRepository.save(student));
        } else {
            throw new Exception(Constants.BAD_REQUEST);
        }
    }

    public void deleteStudent(Integer id) throws Exception {
        Student existingStudent = studentRepository.findStudentById(id);
        if (HelperUtils.isNotNull(existingStudent)) {
            existingStudent.setIsActive(Boolean.FALSE);
            studentRepository.save(existingStudent);
        } else {
            throw new Exception(Constants.BAD_REQUEST);
        }
    }
}
