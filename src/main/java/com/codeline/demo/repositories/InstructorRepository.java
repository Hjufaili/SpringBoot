package com.codeline.demo.repositories;

import com.codeline.demo.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Integer> {
    List<Instructor> findByIsActiveTrue();

}
