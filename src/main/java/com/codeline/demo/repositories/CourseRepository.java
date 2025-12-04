package com.codeline.demo.repositories;

import com.codeline.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByIsActiveTrue();
    Course findByCourseName(String courseName);}
