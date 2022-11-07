package com.example.junitproject.controller.api;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.example.junitproject.controller.dto.request.BookRequest;
import com.example.junitproject.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookApiControllerTest {

	@Autowired
	private BookService bookService;

	@Autowired
	private TestRestTemplate testRestTemplate;

	private static ObjectMapper objectMapper;
	private static HttpHeaders headers;

	@BeforeAll
	public static void init() {
		objectMapper = new ObjectMapper();
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	}

	@DisplayName("DI 테스트")
	@Test
	void givenNone_whenDependencyInjectionToService_thenIsNotNull() {
		Assertions.assertThat(bookService).isNotNull();
	}

	@DisplayName("책 등록 테스트")
	@Test
	void givenBookInfoParserJson_whenSaveBook_thenReturnEqualResponseData() throws JsonProcessingException {
		//given
		BookRequest bookRequest = BookRequest.of("junit", "shin");
		String body = objectMapper.writeValueAsString(bookRequest);

		//when
		HttpEntity<String> request = new HttpEntity<>(body, headers);
		ResponseEntity<String> responseEntity =
			testRestTemplate.exchange("/api/v1/book", HttpMethod.POST, request, String.class);

		//then
		DocumentContext documentContext = JsonPath.parse(responseEntity.getBody());
		String title = documentContext.read("$.body.title");
		String author = documentContext.read("$.body.author");

		Assertions.assertThat(title).isEqualTo(bookRequest.getTitle());
		Assertions.assertThat(author).isEqualTo(bookRequest.getAuthor());
	}

}
