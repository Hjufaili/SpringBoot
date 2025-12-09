package com.codeline.demo.controllers;

import com.codeline.demo.dto.InstructorCreateRequest;
import com.codeline.demo.dto.InstructorCreateResponse;
import com.codeline.demo.entity.Course;
import com.codeline.demo.entity.Instructor;
import com.codeline.demo.service.CourseService;
import com.codeline.demo.service.InstructorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/instructor")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;


    @PostMapping("/create")
    public ResponseEntity<?> createInstructor(
            @RequestBody InstructorCreateRequest requestObj) throws Exception {
        InstructorCreateRequest.validCreateCourseRequest(requestObj);

        InstructorCreateResponse response = instructorService.createInstructor(requestObj);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Instructor>> getAllInstructor() {
        List<Instructor> instructors = instructorService.getAllInstructors();
        return new ResponseEntity<>(instructors, HttpStatus.OK);
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getInstructorById(@PathVariable Integer id) {
        try {
            Instructor instructor = instructorService.getInstructorById(id);
            return new ResponseEntity<>(instructor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Instructor not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateInstructor(@PathVariable Integer id,
                                              @Valid @RequestBody Instructor updateObjFromUser) {
        try {
            Instructor updatedInstructor = instructorService.updateInstructor(updateObjFromUser);
            return new ResponseEntity<>(updatedInstructor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Instructor not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteInstructor(@PathVariable int id) {
        try {
            instructorService.deleteInstructor(id);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Instructor not found", HttpStatus.NOT_FOUND);
        }
    }
}
