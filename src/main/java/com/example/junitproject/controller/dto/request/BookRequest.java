package com.example.junitproject.controller.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.junitproject.domain.Book;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class BookRequest {

	@Size(min = 1, max = 50)
	@NotBlank
	private String title;

	@Size(min = 2, max = 20)
	@NotBlank
	private String author;

	public static BookRequest of(String title, String author) {
		return new BookRequest(title, author);
	}

	public Book toEntity() {
		return Book.builder()
			.title(this.title)
			.author(this.author)
			.build();
	}

}
