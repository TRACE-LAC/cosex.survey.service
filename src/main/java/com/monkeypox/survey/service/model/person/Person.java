package com.monkeypox.survey.service.model.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

    @Column(name = "age", nullable = false, length = (255))
    private String age;
    
    @Column(name = "department", nullable = false, length = (255))
    private String department;
    
    @Column(name = "city", nullable = false, length = (255))
    private String city;
    
    @Column(name = "gender", nullable = false, length = (255))
    private String gender;
    
    @Column(name = "socialSecurity", nullable = false, length = (255))
    private String socialSecurity;
    
    @Column(name = "genderIdentity", nullable = false, length = (255))
    private String genderIdentity;
    
    @Column(name = "sexualOrientation", nullable = false, length = (255))
    private String sexualOrientation;
    
    @Column(name = "sexGender", nullable = false, length = (255))
    private String sexGender;

}