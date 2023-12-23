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

import com.educationsite.StudentDirectory.POJO.AllocateDTO;
import com.educationsite.StudentDirectory.POJO.CourseDTO;
import com.educationsite.StudentDirectory.POJO.StudentDTO;
import com.educationsite.StudentDirectory.Service.StudentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
@Tag(name ="Student")
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
	public ResponseEntity<Object> allocateStudentWithCourse(@RequestBody AllocateDTO allocateData){
		return service.allocate(allocateData);
	}
	
	@PostMapping("/updateCourse/{id}")
	public ResponseEntity<Object> updateCourse(@PathVariable("id") Long id,
			@RequestBody List<CourseDTO> courseList){
		return service.updateCourse(id, courseList,null);
	}
	
	@PutMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id){
		return service.delete(id);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Object> registerNewStudent(@RequestBody StudentDTO dto){
		return service.registerStudent(dto);
	}

}
