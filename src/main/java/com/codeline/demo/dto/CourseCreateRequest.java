package com.codeline.demo.dto;


import com.codeline.demo.entity.Course;
import com.codeline.demo.helper.Constants;
import com.codeline.demo.helper.HelperUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseCreateRequest {

    private String courseName;
    private Integer instructorId;
    private List<Integer> marksId;

    public static Course convertToCourse(CourseCreateRequest request){
        return Course.builder()
                .courseName(request.getCourseName())
                .build();
    }


    public static void validCreateCourseRequest(CourseCreateRequest request) throws Exception{

        if (HelperUtils.isNull(request.getCourseName()) || request.getCourseName().isBlank()){
            throw new Exception (Constants.COURSE_CREATE_REQUEST_NAME_NOT_VALID);
        } else if (HelperUtils.isNull(request.getInstructorId()) || request.getInstructorId()<=0) {
            throw new Exception(Constants.COURSE_CREATE_REQUEST_INSTRUCTOR_ID_NOT_VALID);
        } else if (HelperUtils.isNull(request.getMarksId()) || HelperUtils.isListEmpty(request.getMarksId())) {
            throw new Exception(Constants.COURSE_CREATE_REQUEST_MARK_ID_NOT_VALID);
        }

    }

}
