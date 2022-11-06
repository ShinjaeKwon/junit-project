package com.example.junitproject.controller.dto.request;

import com.example.junitproject.domain.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookUpdateRequest {

	private Long id;
	private String title;
	private String author;

	public static BookUpdateRequest of(Long id, String title, String author) {
		return new BookUpdateRequest(id, title, author);
	}

	public Book toEntity() {
		return Book.builder()
			.id(this.id)
			.title(this.title)
			.author(this.author)
			.build();
	}

}
