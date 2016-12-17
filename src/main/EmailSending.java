package com.complet;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSending {


	public static void email(String email) {

		// Destination Email
		String to = email;

		String host = "pop.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");

		properties.put("mail.smtp.starttls.enable", "true");

		properties.setProperty("mail.smtp.host", host);

	}

}

