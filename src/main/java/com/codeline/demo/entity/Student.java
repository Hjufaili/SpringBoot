package com.codeline.demo.entity;

import com.codeline.demo.dto.PhoneNumberCreateResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@Table(name = "student")
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;

    private Date dateOfBirth;

    private String gender;

    private Boolean isActive;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date updatedDate;


    @OneToMany(mappedBy = "student")
    private List<PhoneNumber> phoneNumbers;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
