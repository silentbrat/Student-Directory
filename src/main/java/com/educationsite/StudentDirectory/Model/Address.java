package com.educationsite.StudentDirectory.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="M_ADDRESS")
@Data
public class Address {
	@Id
	private Long id;
	@Column(name = "address")
	private String address;
	@Column(name = "cityName")
	private String cityName;
	@Column(name = "stateContryName")
	private String stateContryName;
	@Column(name = "pincode")
	private String pincode;

}
