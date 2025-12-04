package com.codeline.demo.service;

import com.codeline.demo.entity.Course;
import com.codeline.demo.entity.Department;
import com.codeline.demo.entity.Marks;
import com.codeline.demo.repositories.CourseRepository;
import com.codeline.demo.repositories.DepartmentRepository;
import com.codeline.demo.repositories.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarksService {
    @Autowired
    MarksRepository marksRepository;

    @Autowired
    CourseRepository courseRepository;


    public Marks createMarks(Marks marks) throws Exception {
        if (marks.getCourse() == null || marks.getCourse().getId() == null) {
            throw new Exception("Valid course is required");
        }

        Course course = courseRepository.findById(marks.getCourse().getId())
                .orElseThrow(() -> new Exception("Course not found"));

        if (!Boolean.TRUE.equals(course.getIsActive())) {
            throw new Exception("Course is inactive");
        }

        marks.setCourse(course);
        marks.setIsActive(true);
        return marksRepository.save(marks);
    }


    public List<Marks> getAllMarks() {
        return marksRepository.findByIsActiveTrue();
    }


    public Marks getMarksById(int id) throws Exception {
        Marks existingMarks = marksRepository.findById(id)
                .orElseThrow(() -> new Exception("Marks not found"));
        if (Boolean.TRUE.equals(existingMarks.getIsActive())) {
            return existingMarks;
        } else {
            throw new Exception("BAD REQUEST");
        }

    }

    public Marks updateMarks(Marks marks) throws Exception {

        Marks existingMarks = marksRepository.findById(marks.getId())
                .orElseThrow(() -> new Exception("Marks not found"));
        if (existingMarks.getIsActive()) {
            marks.setCreatedDate(existingMarks.getCreatedDate());
            marks.setIsActive(existingMarks.getIsActive());
            return marksRepository.save(marks);
        } else {
            throw new Exception("BAD REQUEST");
        }
    }

    public void deleteMarks(int id) throws Exception {
        Marks existingMarks = marksRepository.findById(id)
                .orElseThrow(() -> new Exception("Marks not found"));
        if (existingMarks.getIsActive()) {
            existingMarks.setIsActive(Boolean.FALSE);
            marksRepository.save(existingMarks);
        } else {
            throw new Exception("BAD REQUEST");
        }
    }
}
