package com.educationsite.StudentDirectory.POJO;

import java.util.List;

import lombok.Data;

@Data
public class StudentDTO {
	private Long id;
	private String fullName;
	private String email;
	private String telephoneNo;
	private AddressDTO address;
	private List<String> courseList;
	

}
