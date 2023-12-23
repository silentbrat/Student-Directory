package com.educationsite.StudentDirectory.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educationsite.StudentDirectory.POJO.AuthenticationRequest;
import com.educationsite.StudentDirectory.POJO.AuthenticationResponse;
import com.educationsite.StudentDirectory.POJO.RegisterRequest;
import com.educationsite.StudentDirectory.Security.Auth.AuthenticationService;
import com.educationsite.StudentDirectory.Security.User.ChangePasswordRequest;
import com.educationsite.StudentDirectory.Service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authorization")
public class AuthenticationController {

	private final AuthenticationService service;

	private final UserService userService;

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(service.register(request));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(service.authenticate(request));
	}

	@PostMapping("/refresh-token")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		service.refreshToken(request, response);
	}

	@PatchMapping("/change-password")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) {
		userService.changePassword(request, connectedUser);
		return ResponseEntity.ok().build();
	}

}
