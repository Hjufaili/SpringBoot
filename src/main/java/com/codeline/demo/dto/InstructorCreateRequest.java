package com.codeline.demo.dto;

import com.codeline.demo.entity.Instructor;
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
public class InstructorCreateRequest {

    private String instructorName;
    private Integer departmentId;

    public static Instructor convertToInstructor(InstructorCreateRequest request) throws Exception {
        return Instructor.builder()
                .instructorName(request.getInstructorName())
                .build();
    }

    public static void validCreateCourseRequest(InstructorCreateRequest request) throws Exception {
        if (HelperUtils.isNull(request.getInstructorName()) || request.getInstructorName().isBlank()){
            throw new Exception(Constants.INSTRUCTOR_CREATE_REQUEST_NAME_NOT_VALID);
        } else if (HelperUtils.isNull(request.getDepartmentId()) || request.getDepartmentId()<=0) {
            throw new Exception(Constants.INSTRUCTOR_CREATE_REQUEST_DEPARTMENT_ID_NOT_VALID);
        }

    }
}
