package com.codeline.demo.dto;

import com.codeline.demo.entity.Department;
import com.codeline.demo.entity.Instructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstructorCreateResponse {

    private Integer id;
    private String instructorName;
    private Department department;

    public static InstructorCreateResponse convertToInstructor(Instructor entity){
        return InstructorCreateResponse.builder()
                .id(entity.getId())
                .instructorName(entity.getInstructorName())
                .department(entity.getDepartment())
                .build();
    }
}
