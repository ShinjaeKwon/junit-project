package com.example.junitproject.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.junitproject.controller.dto.request.BookSaveRequest;
import com.example.junitproject.controller.dto.response.Response;
import com.example.junitproject.service.BookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BookApiController {

	private final BookService bookService;

	@PostMapping("/api/v1/book")
	public ResponseEntity<?> saveBook(@RequestBody @Valid BookSaveRequest request, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new RuntimeException(getErrors(bindingResult).toString());
		}
		return new ResponseEntity<>(Response.builder()
			.resultCode("success")
			.message("글 저장 성공")
			.body(bookService.registerBook(request))
			.build(), HttpStatus.CREATED);
	}

	private static Map<String, String> getErrors(BindingResult bindingResult) {
		Map<String, String> errorMap = new HashMap<>();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return errorMap;
	}

}
