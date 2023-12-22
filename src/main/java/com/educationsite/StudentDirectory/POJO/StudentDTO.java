package com.educationsite.StudentDirectory.POJO;

import lombok.Data;

@Data
public class StudentDTO {
	private Long id;
	private String fullName;
	private String email;
	private String telephoneNo;
	private AddressDTO address;
	

}
