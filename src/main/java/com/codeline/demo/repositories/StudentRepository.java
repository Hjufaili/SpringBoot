package com.codeline.demo.repositories;

import com.codeline.demo.entity.Course;
import com.codeline.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    List<Student> findByIsActiveTrue();

    @Query("SELECT c FROM Student c WHERE c.id = :id AND c.isActive=true")
    Student findStudentById(Integer id);
}
