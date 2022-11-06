package com.example.junitproject.util;

import org.springframework.stereotype.Component;

// Mock
@Component
public class MailSenderStub implements MailSender{

	@Override
	public boolean send() {
		return true;
	}
}
