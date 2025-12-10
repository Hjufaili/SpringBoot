package com.codeline.demo.controllers;

import com.codeline.demo.dto.CourseResponseDTO;
import com.codeline.demo.dto.CourseCreateRequest;
import com.codeline.demo.dto.CourseCreateResponse;
import com.codeline.demo.entity.Course;
import com.codeline.demo.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @PostMapping("/createWithRelations")
    public ResponseEntity<?> createCourseWithRelations(
            @RequestBody CourseCreateRequest request) throws Exception {
        CourseCreateRequest.validCreateCourseRequest(request);

            CourseCreateResponse response = courseService.createCourse(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CourseCreateResponse>> getAll() {
        List<CourseCreateResponse> courses = courseService.getAllCoursesDTO();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }



    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Integer id) {
        try {
            Course course = courseService.getCourseById(id);
            return new ResponseEntity<>(course, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Integer id,
                                          @Valid @RequestBody Course updateObjFromUser) {
        try {
            updateObjFromUser.setId(id);
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

