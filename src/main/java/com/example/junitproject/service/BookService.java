package com.example.junitproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.junitproject.controller.dto.request.BookSaveRequest;
import com.example.junitproject.controller.dto.request.BookUpdateRequest;
import com.example.junitproject.controller.dto.response.BookResponse;
import com.example.junitproject.domain.Book;
import com.example.junitproject.repository.BookRepository;
import com.example.junitproject.util.MailSender;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {

	private final BookRepository bookRepository;
	private final MailSender mailSender;

	@Transactional(rollbackFor = RuntimeException.class)
	public BookResponse registerBook(BookSaveRequest request) {
		Book book = bookRepository.save(request.toEntity());
		if (!mailSender.send()) {
			throw new RuntimeException("메일이 전송되지 않았습니다.");
		}

		return book.from();
	}

	@Transactional(readOnly = true)
	public List<BookResponse> getBookList() {
		return bookRepository.findAll().stream()
			.map(Book::from)
			.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public BookResponse getBook(Long id) {
		Book book = bookRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("책 ID 가 존재하지 않습니다."));
		return book.from();
	}

	@Transactional(rollbackFor = RuntimeException.class)
	public void removeBook(Long id) {
		bookRepository.deleteById(id);
	}

	public BookResponse updateBook(BookUpdateRequest request) {
		Book book = bookRepository.findById(request.getId())
			.orElseThrow(() -> new IllegalArgumentException("책 ID 가 존재하지 않습니다."));
		book.update(request.getTitle(), request.getAuthor());
		return book.from();
	}

}
