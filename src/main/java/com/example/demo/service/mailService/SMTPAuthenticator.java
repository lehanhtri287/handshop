package com.example.demo.service.mailService;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {
	public PasswordAuthentication getPasswordAuthentication() {
		ReadFileProperties propertiesLibrary = new ReadFileProperties();
		Properties properties1 = propertiesLibrary.readFileProperties();
		return new PasswordAuthentication(properties1.getProperty("email"), properties1.getProperty("password"));
	}
}
