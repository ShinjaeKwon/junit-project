package com.example.junitproject.controller.dto.request;

import com.example.junitproject.domain.Book;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class BookSaveRequest {

	private String title;
	private String author;

	public static BookSaveRequest of(String title, String author) {
		return new BookSaveRequest(title, author);
	}

	public Book toEntity() {
		return Book.builder()
			.title(this.title)
			.author(this.author)
			.build();
	}

}
