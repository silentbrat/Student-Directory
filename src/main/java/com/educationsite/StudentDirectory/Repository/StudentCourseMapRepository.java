package com.educationsite.StudentDirectory.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.educationsite.StudentDirectory.Model.StudentCourseMap;

@Repository
public interface StudentCourseMapRepository extends JpaRepository<StudentCourseMap, Long>{
	
	@Query("SELECT A FROM StudentCourseMap A WHERE A.student=:studentId")
	public List<StudentCourseMap> getByStudentId(@Param("studentId")Long studentId);
	
	@Query("SELECT A FROM StudentCourseMap A WHERE A.course=:courseId")
	public List<StudentCourseMap> getByCourseId(@Param("courseId")Long courseId);
	
	@Query("SELECT A FROM StudentCourseMap A "
			+ " WHERE A.course=:courseId"
			+ " AND A.student=:studentId")
	public Optional<StudentCourseMap> getByCourseIdAndStudentId(@Param("courseId")Long courseId
			,@Param("studentId")Long studentId);

}
