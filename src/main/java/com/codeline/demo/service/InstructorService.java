package com.codeline.demo.service;

import com.codeline.demo.entity.Course;
import com.codeline.demo.entity.Department;
import com.codeline.demo.entity.Instructor;
import com.codeline.demo.repositories.CourseRepository;
import com.codeline.demo.repositories.DepartmentRepository;
import com.codeline.demo.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class InstructorService {

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CourseRepository courseRepository;

    private static CourseService courseService;


    public Instructor createInstructor(Instructor instructor) throws Exception {
        Department existingDepartment = departmentRepository.findById(instructor.getDepartment().getId())
                .orElseThrow(() -> new Exception("Department not found"));

        Course existingCourse = courseRepository.findById(instructor.getCourse().getId())
                .orElseThrow(() -> new Exception("Course not found"));

        instructor.setIsActive(Boolean.TRUE);
        instructor.setDepartment(existingDepartment);
        existingCourse.setInstructor(instructor); // ← Critical!
        instructor.setCourse(existingCourse);
        return instructorRepository.save(instructor);
    }


    public List<Instructor> getAllInstructors() {
        return instructorRepository.findByIsActiveTrue();
    }


    public Instructor getInstructorById(int id) throws Exception {
        Instructor existingInstructor = instructorRepository.findById(id)
                .orElseThrow(() -> new Exception("Instructor not found"));
        if (existingInstructor.getIsActive()) {
            return existingInstructor;
        } else {
            throw new Exception("BAD REQUEST");
        }

    }

    public Instructor updateInstructor(Instructor instructor) throws Exception {
        Instructor existingInstructor = instructorRepository.findById(instructor.getId())
                .orElseThrow(() -> new Exception("Instructor not found"));

        if (!Boolean.TRUE.equals(existingInstructor.getIsActive())) {
            throw new Exception("Instructor is inactive");
        }

        existingInstructor.setInstructorName(instructor.getInstructorName());

        return instructorRepository.save(existingInstructor);
    }

    public void deleteInstructor(int id) throws Exception {
        Instructor existingInstructor = instructorRepository.findById(id)
                .orElseThrow(() -> new Exception("Instructor not found"));
        if (Boolean.TRUE.equals(existingInstructor.getIsActive())){
            existingInstructor.setIsActive(Boolean.FALSE);
            instructorRepository.save(existingInstructor);
        } else {
            throw new Exception("BAD REQUEST");
        }
    }
}
