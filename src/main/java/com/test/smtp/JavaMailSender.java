package com.test.smtp;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class JavaMailSender {
	
	public JavaMailSenderImpl getMailSender() {
		
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		mailSender.setHost("localhost");
		mailSender.setPort(25);
		mailSender.setUsername("");
		mailSender.setPassword("");

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", "false");
		javaMailProperties.put("mail.smtp.auth", "false");
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.debug", "true");

		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}
}
