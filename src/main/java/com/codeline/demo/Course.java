package com.codeline.demo;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

public class Course {

    private Map<Integer, String> courses = new HashMap<>();
    private Integer couresID = 1;


    @PostMapping("/create")
    public String createCourse(@RequestParam String name) {
        courses.put(couresID, name);
        return "Course create (name: " + name + " ID: " + couresID++ + ")";
    }

    @GetMapping("getAll")
    public Map<Integer, String> getAllCourses() {
        return courses;
    }

    @GetMapping("getById")
    public String getCourseByID(@RequestParam int id) {
        return courses.getOrDefault(id, "course not found");
    }

    @GetMapping("getByID/{id}")
    public String getCourseById(@PathVariable int id) {
        return courses.getOrDefault(id, "course not found");
    }

    @PutMapping("update")
    public String updateCourse(@RequestParam int id, @RequestParam String name) {
        if (courses.containsKey(id)) {
            courses.put(id, name);
            return "Course updated successfully";
        }
        return "Course not found";
    }

}
