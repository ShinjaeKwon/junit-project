package com.example.junitproject.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.junitproject.controller.dto.request.BookSaveRequest;
import com.example.junitproject.controller.dto.response.BookResponse;
import com.example.junitproject.repository.BookRepository;
import com.example.junitproject.util.MailSenderStub;

@DataJpaTest
public class BookServiceTest {

	@Autowired
	private BookRepository bookRepository;

	@DisplayName("책 등록하기 테스트")
	@Test
	void given_when_then() {
		//given
		BookSaveRequest request = new BookSaveRequest("junit", "sjk");

		//stub
		MailSenderStub mailSenderStub = new MailSenderStub();

		//when
		BookService bookService = new BookService(bookRepository, mailSenderStub);
		BookResponse bookResponse = bookService.registerBook(request);

		//then
		Assertions.assertEquals(request.getTitle(), bookResponse.getTitle());
		Assertions.assertEquals(request.getAuthor(), bookResponse.getAuthor());
	}

}
