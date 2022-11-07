package com.example.junitproject.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Response<T> {

	private String resultCode;
	private String message;
	private T body;

}
