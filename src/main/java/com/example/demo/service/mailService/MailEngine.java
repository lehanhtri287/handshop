package com.example.demo.service.mailService;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailEngine {
	public boolean sendEmail(String receiver, String subject, String context) {
		ReadFileProperties propertiesLibrary = new ReadFileProperties();
		Properties propertiesLib = propertiesLibrary.readFileProperties();

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		SMTPAuthenticator auth = new SMTPAuthenticator();

		Session mailSession = Session.getInstance(properties, auth);

		MimeMessage message = new MimeMessage(mailSession);
		try {
			message.setSubject(subject);
			message.setFrom(new InternetAddress(propertiesLib.getProperty("email")));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
			message.setText(context, "UTF-8", "html");

			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
