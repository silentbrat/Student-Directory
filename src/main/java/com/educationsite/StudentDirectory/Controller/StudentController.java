package com.educationsite.StudentDirectory.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educationsite.StudentDirectory.POJO.StudentDTO;
import com.educationsite.StudentDirectory.Service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentService service;
	
	@GetMapping("/getApplicableCourseList")
	public ResponseEntity<Object> getAllCourse(){
		return service.getAllCourse();
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Object> getAllStudent(){
		return service.getAllStudent();
	}
	
	@PostMapping("/allocateStudentToCourse")
	public ResponseEntity<Object> allocateStudentToCourse(@PathVariable("id") Integer courseId,
			@RequestBody List<Long> studentIdList){
		return service.allocateStudentToCourse(courseId, studentIdList);
	}
	
	@PostMapping("/updateCoure/{id}")
	public ResponseEntity<Object> updateCoure(@PathVariable("id") Integer id,
			@RequestBody List<String> courseList){
		return service.updateCoure(id, courseList);
	}
	
	@PutMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Integer id){
		return service.delete(id);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Object> registerNewStudent(@RequestBody StudentDTO dto){
		return service.registerNewStudent(dto);
	}

}
