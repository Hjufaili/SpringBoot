package com.codeline.demo.dto;

import com.codeline.demo.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressCreateResponse {

    private Integer id;
    private String houseNumber;
    private String street;
    private String city;
    private String stateOrProvince;
    private String country;
    private String postalCode;

    public static AddressCreateResponse convertToAddress(Address request){
        return AddressCreateResponse.builder()
                .id(request.getId())
                .houseNumber(request.getHouseNumber())
                .street(request.getStreet())
                .city(request.getCity())
                .stateOrProvince(request.getStateOrProvince())
                .country(request.getCountry())
                .postalCode(request.getPostalCode())
                .build();
    }

    public static Address convertToResponse(AddressCreateResponse response){
        return Address.builder()
                .id(response.getId())
                .houseNumber(response.getHouseNumber())
                .street(response.getStreet())
                .city(response.getCity())
                .stateOrProvince(response.getStateOrProvince())
                .country(response.getCountry())
                .postalCode(response.getPostalCode())
                .build();
    }
}
