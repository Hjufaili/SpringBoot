package com.codeline.demo.dto;


import com.codeline.demo.entity.Address;
import com.codeline.demo.entity.Student;
import com.codeline.demo.helper.Constants;
import com.codeline.demo.helper.HelperUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreateRequest {

    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private String gender;
//    private List<String> phoneNumbers;
//    private Integer addressId;
    private List<PhoneNumberCreateRequest> phoneNumber;
    private AddressCreateRequest address;


    public static Student convertToStudent(StudentCreateRequest request) {
        return Student.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .address(AddressCreateRequest.convertToAddress(request.getAddress()))
                .phoneNumbers(PhoneNumberCreateRequest.convertToPhoneNumber(request.getPhoneNumber()))
                .build();
    }

    public static void validCreateStudentRequest(StudentCreateRequest request) throws Exception {
        if (HelperUtils.isNull(request.getFirstName()) || request.getFirstName().isBlank()) {
            throw new Exception(Constants.STUDENT_CREATE_REQUEST_FIRST_NAME_NOT_VALID);
        } else if (HelperUtils.isNull(request.getLastName()) || request.getLastName().isBlank()) {
            throw new Exception(Constants.STUDENT_CREATE_REQUEST_LAST_NAME_NOT_VALID);
        } else if (HelperUtils.isNull(request.getEmail()) || request.getEmail().isBlank()) {
            throw new Exception(Constants.STUDENT_CREATE_REQUEST_EMAIL_NOT_VALID);
        } else if (HelperUtils.isNull(request.getDateOfBirth()) || request.getDateOfBirth().after(new Date())) {
            throw new Exception(Constants.STUDENT_CREATE_REQUEST_DATE_OF_BIRTH_NOT_VALID);
        } else if (HelperUtils.isNull(request.getGender()) || request.getGender().isBlank()) {
            throw new Exception(Constants.STUDENT_CREATE_REQUEST_GENDER_NOT_VALID);
        } else if (HelperUtils.isNull(request.getPhoneNumber()) || request.getPhoneNumber().isEmpty()) {
            throw new Exception(Constants.STUDENT_CREATE_REQUEST_PHONE_NUMBER_NOT_VALID);
        } else if (HelperUtils.isNull(request.getAddress())) {
            throw new Exception(Constants.STUDENT_CREATE_REQUEST_ADDRESS_NOT_VALID);
        }
    }
}
