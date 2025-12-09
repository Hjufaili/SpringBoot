package com.codeline.demo.dto;

import com.codeline.demo.entity.Department;
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
public class DepartmentCreateRequest {

    private String departmentName;

    public static Department convertToDepartment(DepartmentCreateRequest department){
        return Department.builder()
                .name(department.getDepartmentName())
                .build();
    }

    public static void validCreateDepartmentRequest(DepartmentCreateRequest request) throws Exception {
        if (HelperUtils.isNull(request.getDepartmentName()) || request.getDepartmentName().isBlank()){
            throw new Exception(Constants.DEPARTMENT_CREATE_REQUEST_NAME_NOT_VALID);
        }
    }
}
