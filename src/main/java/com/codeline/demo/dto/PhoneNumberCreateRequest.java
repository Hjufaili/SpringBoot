package com.codeline.demo.dto;

import com.codeline.demo.entity.PhoneNumber;
import com.codeline.demo.helper.Constants;
import com.codeline.demo.helper.HelperUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public static void validCreatePhoneNumberRequest(PhoneNumberCreateRequest request) throws Exception {
        if (HelperUtils.isNull(request.getNumber()) || request.getNumber().isBlank()){
            throw new Exception(Constants.PHONE_NUMBER_CREATE_REQUEST_NUMBER_NOT_VALID);
        } else if (HelperUtils.isNull(request.getCountryCode()) || request.getCountryCode().isBlank()) {
            throw new Exception(Constants.PHONE_NUMBER_CREATE_REQUEST_COUNTRY_CODE_NOT_VALID);
        } else if (HelperUtils.isNull(request.getIsLandLine())) {
            throw new Exception(Constants.PHONE_NUMBER_CREATE_REQUEST_IS_LAND_LINE_NOT_VALID);
        } /*else if (HelperUtils.isNull(request.getStudentId()) || request.getStudentId()<=0) {
            throw new Exception(Constants.PHONE_NUMBER_CREATE_REQUEST_STUDENT_ID_NOT_VALID);
        }*/
    }

}
