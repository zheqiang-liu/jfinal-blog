package test;

import java.security.NoSuchAlgorithmException;

import com.mike.email.Email;

public class Main {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		Email email = new Email();
		email.setSubject("hello");
		email.setContent("fdsaf");
		email.setTo("abap_cloudfoundry@126.com");
		email.send();
    }
     
}
