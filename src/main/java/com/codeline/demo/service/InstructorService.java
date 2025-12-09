package com.codeline.demo.service;

import com.codeline.demo.dto.InstructorCreateRequest;
import com.codeline.demo.dto.InstructorCreateResponse;
import com.codeline.demo.entity.Course;
import com.codeline.demo.entity.Department;
import com.codeline.demo.entity.Instructor;
import com.codeline.demo.helper.Constants;
import com.codeline.demo.helper.HelperUtils;
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


    public InstructorCreateResponse createInstructor(InstructorCreateRequest request) throws Exception {
        Instructor instructor = InstructorCreateRequest.convertToInstructor(request);
        instructor.setIsActive(Boolean.TRUE);

        Department department= departmentRepository.findDepartmentById(request.getDepartmentId());
        if (HelperUtils.isNotNull(department)){
            instructor.setDepartment(department);
        }else {
            throw new Exception(Constants.INSTRUCTOR_CREATE_REQUEST_DEPARTMENT_ID_NOT_VALID);
        }


        return InstructorCreateResponse.convertToInstructor(instructorRepository.save(instructor));
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
