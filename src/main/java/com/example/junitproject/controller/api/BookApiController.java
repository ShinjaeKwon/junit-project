package com.example.junitproject.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		return new ResponseEntity<>(getSuccessResponse("글 저장 성공", bookService.registerBook(request)),
			HttpStatus.CREATED);
	}

	@GetMapping("/api/v1/books")
	public ResponseEntity<?> getBookList() {
		return new ResponseEntity<>(getSuccessResponse("책 목록 불러오기 성공", bookService.getBookList()),
			HttpStatus.OK);
	}

	@GetMapping("/api/v1/book/{id}")
	public ResponseEntity<?> getBookOne(@PathVariable Long id) {
		return new ResponseEntity<>(getSuccessResponse("책 한건 불러오기 성공", bookService.getBook(id)),
			HttpStatus.OK);
	}

	private static Map<String, String> getErrors(BindingResult bindingResult) {
		Map<String, String> errorMap = new HashMap<>();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return errorMap;
	}

	private static Response<Object> getSuccessResponse(String message, Object body) {
		return Response.of("success", message, body);
	}

}
