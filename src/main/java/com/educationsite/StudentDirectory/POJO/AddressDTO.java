package com.educationsite.StudentDirectory.POJO;

import lombok.Data;

@Data
public class AddressDTO {
	private Long id;
	private String address;
	private String cityName;
	private String stateContryName;
	private String pincode;

}
