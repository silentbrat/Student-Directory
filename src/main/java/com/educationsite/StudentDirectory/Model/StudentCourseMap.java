package com.educationsite.StudentDirectory.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="M_ADDRESS")
@Data
public class StudentCourseMap {
	@Id
	private Long id;
	@Column(name = "studentId")
	private Long studentId;
	@Column(name = "courseId")
	private Long courseId;
	@Column(name = "isactive")
	private String isActive;

}
