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
    public ResponseEntity createCourse(@RequestBody Course requestObj) {
        courseService.createCourse(requestObj);
        return ResponseEntity.status(HttpStatus.CREATED).body("Course create (name: " + requestObj.getCourseName() + " ID: " + requestObj.getCourseId() + ")");
    }

/*    public String createCourse(@RequestParam String name) {
        courses.put(courseID, name);
        return "Course create (name: "+ name + " ID: " + courseID++ + ")";
    }*/

    /*@PostMapping("/save")
    public String saveCourse(@RequestBody String name) {
        courses.put(courseID, name);
        return "Course create (name: " + name + " ID: " + courseID++ + ")";
    }*/

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(courseService.getAll());
    }

//    @GetMapping("/getAll")
//    public List<Course> getAllCourses() {
//        List<Course> responseList = new ArrayList<>();
//        for (Course s : coursesList) {
//            if (s.getIsActive()) {
//                responseList.add(s);
//            }
//        }
//        return responseList;
//    }
//    public Map<Integer, String> getAllCourses() {
//        return courses;
//    }

    @GetMapping("/getById")
    public ResponseEntity getCourse(@RequestParam int id) {
        return courseService.getCourse(id);
    }
//    public String getCourseByID(@RequestParam int id) {
//        return courses.getOrDefault(id, "course not found");
//    }

    /*@GetMapping("/getByID/{id}")
    public String getCourseById(@PathVariable int id) {
        return courseService.getCourseById(id);
    }*/

    @GetMapping("/getByName")
    public ResponseEntity getCourseByName(@RequestParam String name) {
        return courseService.getCourseByCourseName(name);
    }

    @PutMapping("/update")
    public ResponseEntity updateCourse(@RequestBody Course updateObjFromUser) {
        return courseService.updateCourse(updateObjFromUser);
    }
//    public String updateCourse(@RequestParam int id, @RequestParam String name) {
//        if (courses.containsKey(id)) {
//            courses.put(id, name);
//            return "Course updated successfully";
//        }
//        return "Course not found";
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable int id) {
        return courseService.deleteCourse(id);
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity restoreCourse(@PathVariable int id) {
        return courseService.restoreCourse(id);

    }


    //    public String deleteCourse(@PathVariable int id) {
//        if (courses.remove(id) != null) {
//            return "Course deleted successfully";
//        }
//        return "Course not found";
//    }
//
//


}
