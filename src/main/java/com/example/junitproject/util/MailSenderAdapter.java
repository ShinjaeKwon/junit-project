package com.example.junitproject.util;

// @Component
public class MailSenderAdapter implements MailSender {

/*
	private Mail mail;

	public MailSenderAdapter() {
		this.mail = new Mail();
	}
*/

	@Override
	public boolean send() {
		return true;
	}
}
