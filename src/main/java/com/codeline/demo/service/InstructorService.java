package com.codeline.demo.service;

import com.codeline.demo.entity.Course;
import com.codeline.demo.entity.Instructor;
import com.codeline.demo.repositories.CourseRepository;
import com.codeline.demo.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {

    @Autowired
    InstructorRepository instructorRepository;


    public Instructor createInstructor(Instructor instructor) {
        instructor.setIsActive(Boolean.TRUE);
        return instructorRepository.save(instructor);
    }


    public List<Instructor> getAllCourses() {
        return instructorRepository.findByIsActiveTrue();
    }


    public Instructor getInstructorById(int id) throws Exception {
        Instructor existingInstructor = instructorRepository.findById(id)
                .orElseThrow(() -> new Exception("Course not found"));
        if (existingInstructor.getIsActive()) {
            return existingInstructor;
        } else {
            throw new Exception("BAD REQUEST");
        }

    }

    public Instructor updateInstructor(Instructor instructor) throws Exception {

        Instructor existingInstructor = instructorRepository.findById(instructor.getId())
                .orElseThrow(() -> new Exception("Course not found"));
        if (existingInstructor.getIsActive()) {
            instructor.setCreatedDate(existingInstructor.getCreatedDate());
            instructor.setIsActive(existingInstructor.getIsActive());
            return instructorRepository.save(instructor);
        } else {
            throw new Exception("BAD REQUEST");
        }
    }

    public void deleteInstructor(int id) throws Exception {
        Instructor existingInstructor = instructorRepository.findById(id)
                .orElseThrow(() -> new Exception("Course not found"));
        if (existingInstructor.getIsActive()) {
            existingInstructor.setIsActive(Boolean.FALSE);
            instructorRepository.save(existingInstructor);
        } else {
            throw new Exception("BAD REQUEST");
        }
    }
}
