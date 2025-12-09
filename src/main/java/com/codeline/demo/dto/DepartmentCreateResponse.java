package com.codeline.demo.dto;


import com.codeline.demo.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentCreateResponse {
    private Integer id;
    private String departmentName;

    public static DepartmentCreateResponse convertToDepartment(Department entity){
        return DepartmentCreateResponse.builder()
                .id(entity.getId())
                .departmentName(entity.getName())
                .build();
    }
}
