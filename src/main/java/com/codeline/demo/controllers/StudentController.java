package com.codeline.demo.controllers;

import com.codeline.demo.dto.StudentCreateRequest;
import com.codeline.demo.dto.StudentCreateResponse;
import com.codeline.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<?> createStudent(
            @RequestBody StudentCreateRequest request) throws Exception {
        StudentCreateRequest.validCreateStudentRequest(request);
        StudentCreateResponse response = studentService.addStudent(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getStudentById/{id}")
    public ResponseEntity<StudentCreateResponse> getStudentById(
            @PathVariable Integer id) throws Exception {

        StudentCreateResponse response = studentService.getStudentById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StudentCreateResponse>> getAllStudents() {
        List<StudentCreateResponse> response = studentService.getAllStudents();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<StudentCreateResponse> updateStudent(
            @PathVariable Integer id,
            @RequestBody StudentCreateRequest request) throws Exception {
        StudentCreateResponse response = studentService.updateStudent(id,StudentCreateRequest.convertToStudent(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) throws Exception {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
