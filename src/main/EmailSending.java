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

		Session session = Session.getDefaultInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(GetCredentials
								.getEmail(), GetCredentials.getEmailPassword());
					}// Specify the User name and the PassWord
				});

		try {

			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));

			// Set Subject: header field
			message.setSubject("Επιτυχής Αποστολή");//

			message.setText("Γεια σας,\nΤο πρόγραμμα μας κατάφερε να εκτελεστεί με επιτυχία"
					+ "\nΕιδικότερα,");

			// Send message
			Transport.send(message);

			// Testing success
			System.out.println("Sent message successfully....");

		} catch (Exception mex) {
			mex.printStackTrace();
		}
	}
}

