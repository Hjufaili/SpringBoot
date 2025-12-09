package com.codeline.demo.repositories;

import com.codeline.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    List<Department> findByIsActiveTrue();

    Department findByName(String name);

    @Query("SELECT c FROM Department c WHERE c.id = :id AND c.isActive=true")
    Department findDepartmentById(Integer id);
}
