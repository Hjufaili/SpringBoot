package com.codeline.demo.controllers;

import com.codeline.demo.entity.Course;
import com.codeline.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;


    @PostMapping("/create")
    public Course createCourse(@RequestBody Course requestObj) {
        Course course = courseService.createCourse(requestObj);
        return course;
    }


    @GetMapping("/getAll")
    public List<Course> getAll() {
        List<Course> courses = courseService.getAllCourses();
        return courses;
    }


    @GetMapping("/getById")
    public Course getCourse(@RequestParam int id) throws Exception {
        return courseService.getCourseById(id);
    }

    @PutMapping("/update")
    public Course updateCourse(@RequestBody Course updateObjFromUser) throws Exception {
        return courseService.updateCourse(updateObjFromUser);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable int id) throws Exception {
        courseService.deleteCourse(id);
        return "SUCCESS";

    }


}
