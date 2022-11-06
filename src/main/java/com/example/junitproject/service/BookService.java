package com.example.junitproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.junitproject.controller.dto.request.BookSaveRequest;
import com.example.junitproject.controller.dto.response.BookResponse;
import com.example.junitproject.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {

	private final BookRepository bookRepository;

	@Transactional(rollbackFor = RuntimeException.class)
	public BookResponse registerBook(BookSaveRequest request) {
		return new BookResponse().from(bookRepository.save(request.toEntity()));
	}

	public List<BookResponse> getBookList() {
		return bookRepository.findAll().stream()
			.map(new BookResponse()::from)
			.collect(Collectors.toList());
	}

}
