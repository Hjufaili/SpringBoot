package com.codeline.demo.dto;


import com.codeline.demo.entity.Course;
import com.codeline.demo.entity.Instructor;
import com.codeline.demo.entity.Marks;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseCreateResponse {
    private Integer id;
    private String courseName;
    private Instructor instructor;
    private List<Marks> marks;

    public static CourseCreateResponse convertToCourse(Course entity){
        return CourseCreateResponse.builder()
                .id(entity.getId())
                .courseName(entity.getCourseName())
                .instructor(entity.getInstructor())
                .marks(entity.getMarks())
                .build();

    }
}
