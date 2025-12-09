package com.codeline.demo.repositories;

import com.codeline.demo.entity.Instructor;
import com.codeline.demo.entity.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Integer> {
    List<Marks> findByIsActiveTrue();

    @Query("SELECT m FROM Marks m WHERE m.id IN :ids AND m.isActive=true")
    List<Marks> findMarkById(List<Integer> ids);
}
