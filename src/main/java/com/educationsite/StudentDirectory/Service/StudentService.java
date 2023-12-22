package com.educationsite.StudentDirectory.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.educationsite.StudentDirectory.Model.Address;
import com.educationsite.StudentDirectory.Model.Student;
import com.educationsite.StudentDirectory.Model.StudentCourseMap;
import com.educationsite.StudentDirectory.POJO.AddressDTO;
import com.educationsite.StudentDirectory.POJO.AllocateDTO;
import com.educationsite.StudentDirectory.POJO.CourseDTO;
import com.educationsite.StudentDirectory.POJO.StudentDTO;
import com.educationsite.StudentDirectory.POJO.StudentDetailDTO;
import com.educationsite.StudentDirectory.Repository.AddressRepository;
import com.educationsite.StudentDirectory.Repository.StudentCourseMapRepository;
import com.educationsite.StudentDirectory.Repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	StudentRepository repository;
	
	@Autowired
	StudentCourseMapRepository mapRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	public ResponseEntity<Object> getAllCourse(){
		List<CourseDTO> list=courseService.getAllCourses();
		if(!list.isEmpty()) {
			return new ResponseEntity<>(list,HttpStatus.OK);
		}
		return new ResponseEntity<>("No Course Found.",HttpStatus.OK);
	}
	
	public ResponseEntity<Object> getAllStudent(){
		List<Student> list=repository.getAllStudentDetail();
		if(list.isEmpty()) return new ResponseEntity<>("No student is present.",HttpStatus.OK);
		List<StudentDetailDTO> dtoList=new ArrayList<>();
		for(Student entity:list) {
			StudentDetailDTO dto=new StudentDetailDTO();
			dto.setCourseList(new ArrayList<>());
			BeanUtils.copyProperties(entity, dto);
			AddressDTO address=new AddressDTO();
			BeanUtils.copyProperties(entity.getAddress(), address);
			dto.setAddress(address);
			for(StudentCourseMap map:entity.getCourseMapList()) {
				CourseDTO courseDto=courseService.getCourseById(map.getId());
				dto.getCourseList().add(courseDto);
			}
			dtoList.add(dto);
		}
		return new ResponseEntity<>(dtoList,HttpStatus.OK);
	}
	
	public ResponseEntity<Object> allocate(AllocateDTO allocateData){
		List<CourseDTO> courseList=new ArrayList<>();
		for(Long courseId:allocateData.getCourseId()) {
			CourseDTO course=new CourseDTO();
			course.setId(courseId);
			courseList.add(course);
		}
		
		return updateCourse(allocateData.getStudentId(),courseList,"Course added.");
	}
	
	@Transactional
	public ResponseEntity<Object> updateCourse(Long id,
			List<CourseDTO> courseList,String msg){
		Student student=repository.geStudentDetailById(id);
		if(student==null) {
			return new ResponseEntity<>("Student not found.",HttpStatus.OK);
		}
		List<StudentCourseMap> needtoAddList=new ArrayList<>();
		List<StudentCourseMap> needtoRemoveList=new ArrayList<>();
		if(!student.getCourseMapList().isEmpty()) {
			for(CourseDTO courseDto:courseList) {
				Optional<StudentCourseMap> mapOpt=student.getCourseMapList().stream()
						.filter(ele-> ele.getId()==courseDto.getId()).findAny();
				if(!mapOpt.isPresent()) {
					needtoAddList.add(mapOpt.get());
				}
			}
			for(StudentCourseMap map:student.getCourseMapList()) {
				Optional<CourseDTO> dtoOpt=courseList.stream()
						.filter(ele-> ele.getId()==map.getId()).findAny();
				if(!dtoOpt.isPresent()) {
					needtoRemoveList.add(map);
				}
			}
			if(!needtoAddList.isEmpty()) mapRepository.saveAll(needtoAddList);
			if(!needtoRemoveList.isEmpty()) mapRepository.deleteAllInBatch(needtoRemoveList);
		}else {
			List<StudentCourseMap> mapList=new ArrayList<>();
			for(CourseDTO courseDto: courseList) {
				StudentCourseMap map=new StudentCourseMap();
				map.setStudent(student);
				map.setCourse(courseDto.getId());
				mapList.add(map);
			}
			mapRepository.saveAll(mapList);
		}
		return new ResponseEntity<>(msg!=null?msg:"Course update.",HttpStatus.OK);
	}
	
	public ResponseEntity<Object> delete(Long id){
		repository.deleteById(id);
		return new ResponseEntity<>("Student delete triggered.",HttpStatus.OK);
	}
	
	@Transactional
	public ResponseEntity<Object> registerStudent(StudentDTO dto){
		Student entity=new Student();
		BeanUtils.copyProperties(dto, entity);
		Address address=new Address();
		BeanUtils.copyProperties(dto.getAddress(), address);
		address=addressRepository.save(address);
		entity.setAddress(address);
		entity=repository.save(entity);
		return new ResponseEntity<>("Student registered.",HttpStatus.OK);
	}
}
