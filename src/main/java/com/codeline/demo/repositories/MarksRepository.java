package com.codeline.demo.repositories;

import com.codeline.demo.entity.Instructor;
import com.codeline.demo.entity.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarksRepository extends JpaRepository<Marks,Integer> {
    List<Marks> findByIsActiveTrue();
    List<Marks> findByCourseIdAndIsActiveTrue(Integer courseId);
    List<Marks> findByStudentNameAndIsActiveTrue(String studentName);
}
