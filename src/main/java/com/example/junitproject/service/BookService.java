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

	@Transactional(readOnly = true)
	public List<BookResponse> getBookList() {
		return bookRepository.findAll().stream()
			.map(new BookResponse()::from)
			.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public BookResponse getBook(Long id) {
		return new BookResponse().from(bookRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("책 ID 가 존재하지 않습니다.")));
	}

	@Transactional(rollbackFor = RuntimeException.class)
	public void removeBook(Long id) {
		bookRepository.deleteById(id);
	}

}
