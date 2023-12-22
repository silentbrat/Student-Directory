package com.educationsite.StudentDirectory.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educationsite.StudentDirectory.Model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
