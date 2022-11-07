package com.example.junitproject.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.junitproject.controller.dto.request.BookSaveRequest;
import com.example.junitproject.controller.dto.response.BookResponse;
import com.example.junitproject.repository.BookRepository;
import com.example.junitproject.util.MailSender;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	@InjectMocks
	private BookService bookService;

	@Mock
	private BookRepository bookRepository;

	@Mock
	private MailSender mailSender;

	@DisplayName("책 등록하기 테스트")
	@Test
	void givenBookInfo_whenSave_thenNotThrow() {
		//given
		BookSaveRequest request = new BookSaveRequest("junit", "sjk");

		//stub
		Mockito.when(bookRepository.save(ArgumentMatchers.any())).thenReturn(request.toEntity());
		Mockito.when(mailSender.send()).thenReturn(true);

		//when
		BookResponse bookResponse = bookService.registerBook(request);

		//then
		Assertions.assertThat(request.getTitle()).isEqualTo(bookResponse.getTitle());
		Assertions.assertThat(request.getAuthor()).isEqualTo(bookResponse.getAuthor());
	}

}
