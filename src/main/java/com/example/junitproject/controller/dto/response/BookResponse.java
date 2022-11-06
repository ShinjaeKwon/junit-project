package com.example.junitproject.controller.dto.response;

import com.example.junitproject.domain.Book;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookResponse {

	private Long id;
	private String title;
	private String author;

	public BookResponse from(Book book) {
		this.id = book.getId();
		this.title = book.getTitle();
		this.author = book.getAuthor();
		return this;
	}
}
