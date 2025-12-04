// src/main/java/com/codeline/demo/dto/CourseResponseDTO.java
package com.codeline.demo.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDTO {
    private Integer id;
    private String courseName;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private InstructorSummary instructor;
    private List<MarkSummary> marks;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InstructorSummary {
        private Integer id;
        private String name;
        private DepartmentSummary department;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DepartmentSummary {
        private Integer id;
        private String name;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MarkSummary {
        private Integer id;
        private String studentName;
        private Integer score;
        private LocalDateTime createdDate;
    }
}