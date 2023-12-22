package com.educationsite.StudentDirectory.POJO;

import java.util.List;

import lombok.Data;

@Data
public class AllocateDTO {
	private Long studentId;
	private List<Long> courseId;

}
