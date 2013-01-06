package com.mike.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EmailAuth extends Authenticator {
	private String userName;
	private String password;

	public EmailAuth() {
		super();
	}

	public EmailAuth(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}

}
