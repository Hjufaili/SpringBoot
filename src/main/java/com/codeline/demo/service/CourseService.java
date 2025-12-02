package com.codeline.demo.service;

import com.codeline.demo.entity.Course;
import com.codeline.demo.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;


    public Course createCourse(Course course) {
        course.setIsActive(Boolean.TRUE);
        return courseRepository.save(course);
    }


    public List<Course> getAllCourses() {
        return courseRepository.findByIsActiveTrue();
    }


    public Course getCourseById(int id) throws Exception {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new Exception("Course not found"));
        if (existingCourse.getIsActive()) {
            return existingCourse;
        } else {
            throw new Exception("BAD REQUEST");
        }

    }

    public Course updateCourse(Course course) throws Exception {

        Course existingCourse = courseRepository.findById(course.getId())
                .orElseThrow(() -> new Exception("Course not found"));
        if (existingCourse.getIsActive()) {
            course.setCreatedDate(existingCourse.getCreatedDate());
            course.setIsActive(existingCourse.getIsActive());
            return courseRepository.save(course);
        } else {
            throw new Exception("BAD REQUEST");
        }
    }

    public void deleteCourse(int id) throws Exception {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new Exception("Course not found"));
        if (existingCourse.getIsActive()) {
            existingCourse.setIsActive(Boolean.FALSE);
            courseRepository.save(existingCourse);
        } else {
            throw new Exception("BAD REQUEST");
        }
    }

}
