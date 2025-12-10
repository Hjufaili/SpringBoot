package com.codeline.demo.dto;

import com.codeline.demo.entity.PhoneNumber;
import com.codeline.demo.entity.Student;
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
public class StudentCreateResponse {


    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private String gender;
    private List<PhoneNumber> phoneNumbers;
    private Integer addressId;

    public static StudentCreateResponse convertToStudent(Student request){
        return StudentCreateResponse.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .phoneNumbers(request.getPhoneNumbers())
                .addressId(request.getAddress().getId())
                .build();
    }
}
