package com.educationsite.StudentDirectory.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="M_STUDENT")
@Data
public class Student {
	@Id
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

}
