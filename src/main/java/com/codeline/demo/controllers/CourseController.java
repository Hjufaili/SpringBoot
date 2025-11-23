package com.codeline.demo.controllers;

import com.codeline.demo.entity.Course;
import com.codeline.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;


    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course requestObj) {
        Course course = courseService.createCourse(requestObj);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Course>> getAll() {
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }


    @GetMapping("/getById")
    public ResponseEntity<?> getCourse(@RequestParam int id) {
        try {
            Course course = courseService.getCourseById(id);
            return new ResponseEntity<>(course, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCourse(@RequestBody Course updateObjFromUser) {
        try {
            Course updatedCourse = courseService.updateCourse(updateObjFromUser);
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK); 
        } catch (Exception e) {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable int id) {
        try {
            courseService.deleteCourse(id);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }
    }
}

