package com.example.junitproject.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.junitproject.domain.Book;

/*
 * @DataJpaTest : DB와 관련된 컴포넌트만 메모리에 로딩
 * @Autowired : Dependency Injection By Test
 * */
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;

	@DisplayName("책 등록 테스트")
	@Test
	void givenBookInfo_whenSave_thenNotThrow() {
		//given
		String title = "junit5";
		String author = "sjk";
		Book book = Book.builder()
			.title(title)
			.author(author)
			.build();

		//when
		Book persistentBook = bookRepository.save(book);

		//then
		Assertions.assertEquals(title, persistentBook.getTitle());
		Assertions.assertEquals(author, persistentBook.getAuthor());
		Assertions.assertEquals(book, persistentBook);

	}

}
