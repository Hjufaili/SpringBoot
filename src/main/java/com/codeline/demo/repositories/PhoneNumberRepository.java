package com.codeline.demo.repositories;

import com.codeline.demo.dto.PhoneNumberCreateResponse;
import com.codeline.demo.entity.Course;
import com.codeline.demo.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber,Integer> {

    List<PhoneNumber> findByIsActiveTrue();

    @Query("SELECT c FROM PhoneNumber c WHERE c.number IN :number AND c.isActive = true")
    List<PhoneNumber> findPhoneNumberByNumber(List<String> number);

    PhoneNumber findPhoneNumberByNumber(String number);
}
