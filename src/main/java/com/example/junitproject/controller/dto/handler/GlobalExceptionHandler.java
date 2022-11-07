package com.example.junitproject.controller.dto.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.junitproject.controller.dto.response.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> apiException(RuntimeException e) {
		return new ResponseEntity<>(Response.builder()
			.resultCode("error")
			.message(e.getMessage())
			.build(), HttpStatus.BAD_REQUEST);
	}

}
