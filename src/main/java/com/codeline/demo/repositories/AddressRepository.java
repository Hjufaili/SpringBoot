package com.codeline.demo.repositories;

import com.codeline.demo.entity.Address;
import com.codeline.demo.entity.Course;
import com.codeline.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
    List<Address> findByIsActiveTrue();
    @Query("SELECT c FROM Address c WHERE c.id = :id AND c.isActive=true")
    Address findAddressById(Integer id);
}
