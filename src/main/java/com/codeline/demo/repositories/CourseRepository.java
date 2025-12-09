package com.codeline.demo.repositories;

import com.codeline.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByIsActiveTrue();

    @Query("SELECT c FROM Course c WHERE c.id = :id AND c.isActive=true")
    Course findCourseById(Integer id);

}
