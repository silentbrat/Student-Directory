package com.educationsite.StudentDirectory.Config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.educationsite.StudentDirectory.POJO.CustomException;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(value = CustomException.class)
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler
	protected ResponseEntity<Object> othereConflict(Exception ex, WebRequest request) {
		return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}
