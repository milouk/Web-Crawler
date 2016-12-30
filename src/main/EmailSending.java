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

		// Credentials for the Google mail Service Account
		GetCredentials.emailCredentials("d:\\Users\\Pan\\Desktop\\JAVA_KOMPLE\\EmailCredentials.txt");

		String from = GetCredentials.getEmail();

		String host = "pop.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Necessary port, Google mail Service protocol
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");

		properties.put("mail.smtp.starttls.enable", "true");

		properties.setProperty("mail.smtp.host", host);

		Session session = Session.getDefaultInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(GetCredentials
								.getEmail(), GetCredentials.getEmailPassword());
					}// Specify the User name and the Password
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
			message.setSubject("Sent message successfully...");//

			// Content of the E-mail
			message.setText("Phoneutria completed the task successfully." + "\n\nMore Details: "
					+ "\n\n•Searched " + Mainclass.getLayers() + " Layer(s)."
					+ "\n\n•Used URLs: \n•" + Mainclass.getLink1() + ", \n•" + Mainclass.getLink2()
					+ ", \n•" + Mainclass.getLink3() + "\n\n•Created: " + Mainclass.getFinalist().size()
					+ " documents." + "\n•This was the #"
					+ Mainclass.getRun_times() + " time.\n" + "\nTime Started: "
					+ dateFormat.format(Mainclass.getDate()) + "\nTime finished: " + dateFormat.format(date)
					+ "\n\n\n•The Database will be updated in 24 hours!");

			// Send message
			Transport.send(message);

			// Testing success
			System.out.println("Sent message successfully...");

		} catch (Exception mex) {
			// Exception
			mex.printStackTrace();
		}
	}
}

