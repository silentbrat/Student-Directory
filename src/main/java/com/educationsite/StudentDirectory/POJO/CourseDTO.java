package com.educationsite.StudentDirectory.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
	
	private Long id;
	private String courseName;
	private String isActive;
	private String description;

}
