package com.mike.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.mike.core.BlogConstants;


public class Email {
	private String subject;
	private String content;
	private InternetAddress to;
	private InternetAddress from;

	public Email() {
		try {
			this.from = new InternetAddress(BlogConstants.EMAIL_USERNAME);
			this.to = new InternetAddress(BlogConstants.EMAIL_USERNAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.subject = "From " + BlogConstants.TITLE;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public InternetAddress getTo() {
		return to;
	}

	public void setTo(String to) {
		try {
			this.to = new InternetAddress(to);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public InternetAddress getFrom() {
		return from;
	}

	public void setFrom(String from) {
		try {
			this.from = new InternetAddress(from);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void send() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Properties props = new Properties();
				props.setProperty("mail.transport.protocol", BlogConstants.EMAIL_PROTOCOL);
				props.setProperty("mail.smtp.host", BlogConstants.EMAIL_HOST);
				Authenticator auth = new EmailAuth(BlogConstants.EMAIL_USERNAME,BlogConstants.EMAIL_PASSWORD);
				props.setProperty("mail.smtp.auth", "true");
				Session session = Session.getDefaultInstance(props, auth);
				MimeMessage message = new MimeMessage(session);
				try {
					message.setSubject(getSubject());
					message.setFrom(getFrom());
					message.setRecipient(RecipientType.TO, getTo());
					message.setSentDate(new Date());
					message.setContent(content, "text/html;charset=UTF-8");
					Transport.send(message);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
