package com.codeline.demo.controllers;

import com.codeline.demo.entity.Course;
import com.codeline.demo.entity.Department;
import com.codeline.demo.service.CourseService;
import com.codeline.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    @PostMapping("/create")
    public ResponseEntity<Department> createDepartment(@RequestBody Department requestObj) {
        Department department = departmentService.createDepartment(requestObj);
        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable Integer id) {
        try {
            Department department = departmentService.getDepartmentById(id);
            return new ResponseEntity<>(department, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Department not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable Integer id, @RequestBody Department updateObjFromUser) {
        try {
            updateObjFromUser.setId(id);
            Department updatedDepartment = departmentService.updateDepartment(updateObjFromUser);
            return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Department not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable int id) {
        try {
            departmentService.deleteDepartment(id);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Department not found", HttpStatus.NOT_FOUND);
        }
    }
}
