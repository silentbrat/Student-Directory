package com.educationsite.StudentDirectory.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educationsite.StudentDirectory.Security.User.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);
  
  List<User> getAllByEmail(String email);

}
