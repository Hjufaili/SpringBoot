package com.codeline.demo.service;

import com.codeline.demo.entity.Course;
import com.codeline.demo.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;


    public Course createCourse(Course course) {
        course.setCreatedDate(new Date());
        course.setIsActive(Boolean.TRUE);
        return courseRepository.save(course);
    }


    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }


    public Course getCourseById(int id) throws Exception {
        Course existingCourse = courseRepository.findById(id).get();
        if (existingCourse != null && existingCourse.getIsActive()) {
            return existingCourse;
        } else {
            throw new Exception("BAD REQUEST");
        }

    }

    public Course updateCourse(Course course) throws Exception {

        Course existingCourse = courseRepository.findById(course.getCourseId()).get();
        if (existingCourse != null && existingCourse.getIsActive()) {
            course.setUpdateDate(new Date());
            return courseRepository.save(course);
        } else {
            throw new Exception("BAD REQUEST");
        }
    }

    public void deleteCourse(int id) throws Exception {
        Course existingCourse = courseRepository.findById(id).get();
        if (existingCourse != null && existingCourse.getIsActive()) {
            existingCourse.setUpdateDate(new Date());
            existingCourse.setIsActive(Boolean.FALSE);
            courseRepository.save(existingCourse);
        } else {
            throw new Exception("BAD REQUEST");
        }
    }

}
