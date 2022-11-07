package com.example.junitproject.controller.dto.response;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BookListResponse {
	List<BookResponse> items;

	public static BookListResponse of(List<BookResponse> items) {
		return new BookListResponse(items);
	}

}
