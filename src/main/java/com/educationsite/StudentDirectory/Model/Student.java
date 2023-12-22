package com.educationsite.StudentDirectory.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="M_STUDENT")
@Data
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name = "fullName",nullable = false)
	private String fullName;
	@Column(name = "email",nullable = false,unique = true)
	private String email;
	@Column(name = "telephoneNo")
	private String telephoneNo;
	
	@OneToOne
	@JoinColumn(name = "address")
	private Address address;
	
	@OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<StudentCourseMap> courseMapList;

}
