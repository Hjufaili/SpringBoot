package com.codeline.demo.controllers;


import com.codeline.demo.dto.MarksCreateRequest;
import com.codeline.demo.dto.MarksCreateResponse;
import com.codeline.demo.entity.Department;
import com.codeline.demo.entity.Marks;
import com.codeline.demo.service.DepartmentService;
import com.codeline.demo.service.MarksService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marks")
@CrossOrigin(origins = "*")
public class MarksController {

    @Autowired
    private MarksService marksService;


    @PostMapping("/create")
    public ResponseEntity<?> createMarks(
            @RequestBody MarksCreateRequest requestObj) throws Exception {
        MarksCreateRequest.validCreateMarksRequest(requestObj);
        MarksCreateResponse marks = marksService.createMarks(requestObj);
        return new ResponseEntity<>(marks, HttpStatus.CREATED);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Marks>> getAllMarks() {
        List<Marks> marks = marksService.getAllMarks();
        return new ResponseEntity<>(marks, HttpStatus.OK);
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getMarksById(@PathVariable Integer id) {
        try {
            Marks marks = marksService.getMarksById(id);
            return new ResponseEntity<>(marks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Marks not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMarks(@PathVariable Integer id,
                                         @Valid @RequestBody Marks updateObjFromUser) {
        try {
            updateObjFromUser.setId(id);
            Marks updatedMarks = marksService.updateMarks(updateObjFromUser);
            return new ResponseEntity<>(updatedMarks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Marks not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMarks(@PathVariable int id) {
        try {
            marksService.deleteMarks(id);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Marks not found", HttpStatus.NOT_FOUND);
        }
    }
}
