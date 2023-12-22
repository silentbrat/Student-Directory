package com.educationsite.StudentDirectory.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.educationsite.StudentDirectory.Model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	
	@Query("SELECT a FROM Student a "
			+ " LEFT JOIN a.courseMapList s"
			+ " LEFT JOIN a.address aa")
	public List<Student> getAllStudentDetail();
	
	@Query("SELECT a FROM Student a "
			+ " LEFT JOIN a.courseMapList s"
			+ " LEFT JOIN a.address aa"
			+ " WHERE a.id=:studentId")
	public Student geStudentDetailById(@Param("studentId")Long studentId);
	
	

}
