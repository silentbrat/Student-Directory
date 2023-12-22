package com.educationsite.StudentDirectory.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educationsite.StudentDirectory.Model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
