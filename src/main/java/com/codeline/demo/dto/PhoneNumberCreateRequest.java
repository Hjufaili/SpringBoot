package com.codeline.demo.dto;

import com.codeline.demo.entity.PhoneNumber;
import com.codeline.demo.helper.Constants;
import com.codeline.demo.helper.HelperUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumberCreateRequest {

    private String number;
    private String countryCode;
    private Boolean isLandLine;
//    private Integer studentId;

    public static PhoneNumber convertToPhoneNumber(PhoneNumberCreateRequest request) {
        return PhoneNumber.builder()
                .number(request.getNumber())
                .countryCode(request.getCountryCode())
                .isLandLine(request.getIsLandLine())
                .build();
    }

    public static List<PhoneNumber> convertToPhoneNumber(List<PhoneNumberCreateRequest> requestList) {
        List<PhoneNumber> entities = new ArrayList<>();
        for (PhoneNumberCreateRequest dto : requestList) {
            entities.add(convertToPhoneNumber(dto));
        }
        return entities;
    }

    public static void validCreatePhoneNumberRequest(List<PhoneNumberCreateRequest> request) throws Exception {
        for (PhoneNumberCreateRequest phone: request){
            if (HelperUtils.isNull(phone.getNumber()) || phone.getNumber().isBlank()) {
                throw new Exception(Constants.PHONE_NUMBER_CREATE_REQUEST_NUMBER_NOT_VALID);
            } else if (HelperUtils.isNull(phone.getCountryCode()) || phone.getCountryCode().isBlank()) {
                throw new Exception(Constants.PHONE_NUMBER_CREATE_REQUEST_COUNTRY_CODE_NOT_VALID);
            } else if (HelperUtils.isNull(phone.getIsLandLine())) {
                throw new Exception(Constants.PHONE_NUMBER_CREATE_REQUEST_IS_LAND_LINE_NOT_VALID);
            } /*else if (HelperUtils.isNull(request.getStudentId()) || request.getStudentId()<=0) {
            throw new Exception(Constants.PHONE_NUMBER_CREATE_REQUEST_STUDENT_ID_NOT_VALID);
        }*/
        }

    }

}
