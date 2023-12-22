package com.educationsite.StudentDirectory.Model;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name ="M_COURSE")
@Data
public class Course {
	
	@Id
	private Long id;
	@Column(name="coursename",length = 50,unique = true,nullable = false)
	private String courseName;
	@Column(name="isactive",nullable = false)
	@NonNull
	private String isActive;
	@Column(name="description",length = 1024)
	private String description;

}
