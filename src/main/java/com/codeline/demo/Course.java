package com.codeline.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
