package com.codeline.demo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDTO {

    @NotBlank(message = "Course name is required")
    private String courseName;

    @NotNull(message = "Instructor is required")
    @Valid
    private InstructorRequest instructor;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InstructorRequest {
        private Integer id;
        @NotBlank(message = "Instructor name is required")
        private String name;

        @NotNull(message = "Department is required")
        @Valid
        private DepartmentRequest department;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DepartmentRequest {
        private Integer id; // null → create new
        @NotBlank(message = "Department name is required")
        private String name;
    }

    @Builder.Default
    private List<MarkRequest> marks = List.of();

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MarkRequest {
        @NotBlank(message = "Student name is required")
        private String studentName;

        @NotNull
        private Integer score;
    }
}