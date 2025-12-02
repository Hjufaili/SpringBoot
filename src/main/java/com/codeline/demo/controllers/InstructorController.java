package com.codeline.demo.controllers;

import com.codeline.demo.entity.Course;
import com.codeline.demo.entity.Instructor;
import com.codeline.demo.service.CourseService;
import com.codeline.demo.service.InstructorService;
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
    public ResponseEntity<Instructor> createInstructor (@RequestBody Instructor requestObj) throws Exception {
        Instructor instructor = instructorService.createInstructor(requestObj);
        return new ResponseEntity<>(instructor, HttpStatus.CREATED);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Instructor>> getAllInstructor() {
        List<Instructor> instructors = instructorService.getAllCourses();
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
    public ResponseEntity<?> updateInstructor(@PathVariable Integer id, @RequestBody Instructor updateObjFromUser) {
        try {
            updateObjFromUser.setId(id);
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
