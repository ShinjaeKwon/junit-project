package com.example.junitproject.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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

		//when

		//then

	}
}
