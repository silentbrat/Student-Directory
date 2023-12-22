package com.educationsite.StudentDirectory.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.educationsite.StudentDirectory.POJO.StudentDTO;
import com.educationsite.StudentDirectory.Repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository repository;
	
	public ResponseEntity<Object> getAllCourse(){
		return null;
	}
	
	public ResponseEntity<Object> getAllStudent(){
		return null;
	}
	
	public ResponseEntity<Object> allocateStudentToCourse(Integer courseId,
			@RequestBody List<Long> studentIdList){
		return null;
	}
	
	public ResponseEntity<Object> updateCoure(Integer id,
			@RequestBody List<String> courseList){
		return null;
	}
	
	public ResponseEntity<Object> delete(Integer id){
		return null;
	}
	
	public ResponseEntity<Object> registerNewStudent(StudentDTO dto){
		return null;
	}
}
