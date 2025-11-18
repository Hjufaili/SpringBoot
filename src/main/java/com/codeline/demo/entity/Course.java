package com.codeline.demo.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class Course {

    private Integer courseId;
    private String courseName;
    private String instructor;
    private Date createdDate;
    private Date updateDate;
    private Boolean isActive;
    private Date deletedAt;

}
