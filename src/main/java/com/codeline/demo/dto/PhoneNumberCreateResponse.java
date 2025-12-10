package com.codeline.demo.dto;

import com.codeline.demo.entity.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumberCreateResponse {

    private Integer id;
    private String number;
    private String countryCode;
    private Boolean isLandLine;

    public static PhoneNumberCreateResponse convertToPhoneNumber(PhoneNumber request) {
        return PhoneNumberCreateResponse.builder()
                .id(request.getId())
                .number(request.getNumber())
                .countryCode(request.getCountryCode())
                .isLandLine(request.getIsLandLine())
                .build();
    }
}
