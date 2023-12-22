package com.educationsite.StudentDirectory.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.educationsite.StudentDirectory.POJO.CourseDTO;

@Service
public class CourseService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${course.baseurl}")
	private String courseBaseUrl;
	
	@Value("${course.get-all-Course}")
	private String getAllEndPoint;
	
	@Value("${course.get-Course-id}")
	private String getByIdEndPoint;
	
	public List<CourseDTO> getAllCourses(){
		 String uri = new StringBuilder().append(courseBaseUrl)
				 .append(getAllEndPoint).toString();
		List<?> response= restTemplate.getForObject(uri, List.class);
		return (List<CourseDTO>) response;
	}
	
	public CourseDTO getCourseById(Long id){
		 String uri = new StringBuilder().append(courseBaseUrl)
				 .append(getByIdEndPoint).append("/{id}").toString();
		 ResponseEntity<CourseDTO> response= restTemplate.getForEntity(uri, CourseDTO.class,id);
			return response.getBody();
	}

}
