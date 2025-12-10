package com.codeline.demo.dto;

import com.codeline.demo.entity.Address;
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
public class AddressCreateRequest {

    private String houseNumber;
    private String street;
    private String city;
    private String stateOrProvince;
    private String country;
    private String postalCode;

    public static Address convertToAddress(AddressCreateRequest request){
        return Address.builder()
                .houseNumber(request.getHouseNumber())
                .street(request.getStreet())
                .city(request.getCity())
                .stateOrProvince(request.getStateOrProvince())
                .country(request.getCountry())
                .postalCode(request.getPostalCode())
                .build();
    }

    public static AddressCreateRequest convertToRequest(Address address){

        return AddressCreateRequest.builder()
                .houseNumber(address.getHouseNumber())
                .street(address.getStreet())
                .city(address.getCity())
                .stateOrProvince(address.getStateOrProvince())
                .country(address.getCountry())
                .postalCode(address.getPostalCode())
                .build();
    }

    public static void validCreateAddressRequest(AddressCreateRequest request) throws Exception{
        if(HelperUtils.isNull(request.getHouseNumber()) || request.getHouseNumber().isBlank()){
            throw new Exception(Constants.ADDRESS_CREATE_REQUEST_HOUSE_NUMBER_NOT_VALID);
        } else if (HelperUtils.isNull(request.getStreet()) || request.getStreet().isBlank()){
            throw new Exception(Constants.ADDRESS_CREATE_REQUEST_STREET_NOT_VALID);
        }else if (HelperUtils.isNull(request.getCity()) || request.getCity().isBlank()){
            throw new Exception(Constants.ADDRESS_CREATE_REQUEST_CITY_NOT_VALID);
        }else if (HelperUtils.isNull(request.getStateOrProvince()) || request.getStateOrProvince().isBlank()){
            throw new Exception(Constants.ADDRESS_CREATE_REQUEST_STATE_OR_PROVINCE_NOT_VALID);
        }else if (HelperUtils.isNull(request.getCountry()) || request.getCountry().isBlank()){
            throw new Exception(Constants.ADDRESS_CREATE_REQUEST_COUNTRY_NOT_VALID);
        }else if (HelperUtils.isNull(request.getPostalCode()) || request.getPostalCode().isBlank()){
            throw new Exception(Constants.ADDRESS_CREATE_REQUEST_POSTAL_CODE_NOT_VALID);
        }
    }
}
