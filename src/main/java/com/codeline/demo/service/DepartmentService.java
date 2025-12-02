package com.codeline.demo.service;

import com.codeline.demo.entity.Course;
import com.codeline.demo.entity.Department;
import com.codeline.demo.repositories.CourseRepository;
import com.codeline.demo.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;


    public Department createDepartment(Department department) {
        department.setIsActive(Boolean.TRUE);
        return departmentRepository.save(department);
    }


    public List<Department> getAllDepartments() {
        return departmentRepository.findByIsActiveTrue();
    }


    public Department getDepartmentById(int id) throws Exception {
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new Exception("Department not found"));
        if (existingDepartment.getIsActive()) {
            return existingDepartment;
        } else {
            throw new Exception("BAD REQUEST");
        }

    }

    public Department updateDepartment(Department department) throws Exception {

        Department existingDepartment = departmentRepository.findById(department.getId())
                .orElseThrow(() -> new Exception("Department not found"));
        if (existingDepartment.getIsActive()) {
            department.setCreatedDate(existingDepartment.getCreatedDate());
            department.setIsActive(existingDepartment.getIsActive());
            return departmentRepository.save(department);
        } else {
            throw new Exception("BAD REQUEST");
        }
    }

    public void deleteDepartment(int id) throws Exception {
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new Exception("Department not found"));
        if (existingDepartment.getIsActive()) {
            existingDepartment.setIsActive(Boolean.FALSE);
            departmentRepository.save(existingDepartment);
        } else {
            throw new Exception("BAD REQUEST");
        }
    }
}
