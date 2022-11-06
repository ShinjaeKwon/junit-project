package com.example.junitproject.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.example.junitproject.domain.Book;

/*
 * Annotation Explanation
 * @DataJpaTest : DB와 관련된 컴포넌트만 메모리에 로딩
 * @Autowired : Dependency Injection By Test
 * @BeforeAll : 테스트 시작전에 한번만 실행, 클래스 단위
 * @BeforeEach : 각 테스트 시작전에 실행, 트랜잭션은 각 테스트마다 묶임
 * @Sql : 테스트 시작전에 직접 SQL 실행
 *
 * 1. 테스트 메서드는 순서 보장이 안된다 - Order() 어노테이션 사용시 순서 제어 가능
 * 2. 테스트 메서드가 하나 실행 후 종료하면 데이터가 초기화된다. - Transactional() 어노테이션으로 인해 초기화
 * 2-1. primary key auto_increment -> 이 값은 초기화 되지 않는다.
 */
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;

	@BeforeEach
	public void insertData() {
		String title = "junit";
		String author = "shin";
		Book book = Book.builder()
			.title(title)
			.author(author)
			.build();
		bookRepository.save(book);
	}

	@DisplayName("책 등록 테스트")
	@Test
	void givenBookInfo_whenSave_thenReturnPersistentBook() {
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

	@DisplayName("책 목록보기 테스트")
	@Test
	void givenBookInfo_whenFindAll_thenReturnPersistentBookList() {
		//given
		String title = "junit";
		String author = "shin";

		//when
		List<Book> books = bookRepository.findAll();

		//then
		Assertions.assertEquals(title, books.get(0).getTitle());
		Assertions.assertEquals(author, books.get(0).getAuthor());
	}

	@DisplayName("책 한건 보기 테스트")
	@Sql("classpath:db/tableInit.sql")
	@Test
	void givenBookInfo_whenFindById_thenReturnPersistentBook() {
		//given
		String title = "junit";
		String author = "shin";

		//when
		Book persistentBook = bookRepository.findById(1L).get();

		//then
		Assertions.assertEquals(title, persistentBook.getTitle());
		Assertions.assertEquals(author, persistentBook.getAuthor());
	}

	@DisplayName("책 삭제 테스트")
	@Sql("classpath:db/tableInit.sql")
	@Test
	void given_when_then() {
		//given
		Long id = 1L;

		//when
		bookRepository.deleteById(id);

		//then
		Assertions.assertFalse(bookRepository.findById(id).isPresent());
	}

}
