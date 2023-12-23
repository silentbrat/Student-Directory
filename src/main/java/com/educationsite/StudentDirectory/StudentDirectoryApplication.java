package com.educationsite.StudentDirectory;

import static com.educationsite.StudentDirectory.Security.User.Role.ADMIN;
import static com.educationsite.StudentDirectory.Security.User.Role.MANAGER;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.educationsite.StudentDirectory.POJO.RegisterRequest;
import com.educationsite.StudentDirectory.Security.Auth.AuthenticationService;


@SpringBootApplication
public class StudentDirectoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentDirectoryApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Aman")
					.lastname("Sahu")
					.email("aman.sahu@mail.com")
					.password("password")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var manager = RegisterRequest.builder()
					.firstname("Manager")
					.lastname("User")
					.email("manager@mail.com")
					.password("password")
					.role(MANAGER)
					.build();
			System.out.println("Manager token: " + service.register(manager).getAccessToken());

		};
	}
}
