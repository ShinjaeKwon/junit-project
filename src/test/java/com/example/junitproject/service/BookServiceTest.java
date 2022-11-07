package com.example.junitproject.service;

import java.util.List;
import java.util.Optional;

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
import com.example.junitproject.domain.Book;
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
	void givenBookInfo_whenSave_thenReturnBookResponse() {
		//given
		BookSaveRequest request = new BookSaveRequest("junit", "sjk");

		//stub
		Mockito.when(bookRepository.save(ArgumentMatchers.any())).thenReturn(request.toEntity());
		Mockito.when(mailSender.send()).thenReturn(true);

		//when
		BookResponse bookResponse = bookService.registerBook(request);

		//then
		Assertions.assertThat(bookResponse.getTitle()).isEqualTo(request.getTitle());
		Assertions.assertThat(bookResponse.getAuthor()).isEqualTo(request.getAuthor());
	}

	@DisplayName("책 목록 보기 테스트")
	@Test
	void givenNone_whenGetBookList_thenReturnResponseList() {
		//given

		//stub
		List<Book> books = List.of(
			new Book(1L, "junit", "sjk"),
			new Book(2L, "spring", "shin"));
		Mockito.when(bookRepository.findAll()).thenReturn(books);

		//when
		List<BookResponse> bookResponseList = bookService.getBookList();

		//then
		Assertions.assertThat(bookResponseList.get(0).getTitle()).isEqualTo("junit");
		Assertions.assertThat(bookResponseList.get(0).getAuthor()).isEqualTo("sjk");
		Assertions.assertThat(bookResponseList.get(1).getTitle()).isEqualTo("spring");
		Assertions.assertThat(bookResponseList.get(1).getAuthor()).isEqualTo("shin");
	}

	@DisplayName("책 한건 보기 테스트")
	@Test
	void givenBookInfo_whenGetBook_thenReturnBookResponse() {
		//given
		Long id = 1L;
		Book book = new Book(1L, "junit", "sjk");

		//stub
		Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(book));

		//when
		BookResponse bookResponse = bookService.getBook(id);

		//then
		Assertions.assertThat(bookResponse.getTitle()).isEqualTo(book.getTitle());
		Assertions.assertThat(bookResponse.getAuthor()).isEqualTo(book.getAuthor());
	}

}
