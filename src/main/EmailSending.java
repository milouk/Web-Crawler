package com.complet;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * <h1>Send an Email - EmailSending.java</h1> The EmailSending Class is able to send an email using
 * the simple mail transfer protocol (S.M.T.P.)
 * <p>
 * <b>Note: </b>Must get a valid E-mail in order to execute the process,
 * otherwise, it will not work.
 * <p>
 * @author Complet
 * @version 5.0
 * @since 2016-12-29
 *
 */

public class EmailSending {

	/**
	 * <p>
	 * This is the only method of the class. Using the correct properties, it is
	 * able to connect with the google mail Service and send the E-mail
	 * successfully.
	 * <p>
	 * Google mail service demands a valid account. And therefor, we use
	 * Credentials that draw the account settings from the EmailCredentials.txt
	 * document.
	 * <p>
	 * <b>The content of the E-mail:</b><br>
	 * <li>Number of Layers used <br>
	 * <li>Url's that were crawled <br>
	 * <li>Results (Number of Links) <br>
	 * <li>Number of Run_times (Our program can literally run forever, updating
	 * the database) <br>
	 * <li>Date and Time the program started (for the given Run_time)<br>
	 * <li>Date and TIme the program "ended" (for the given Run_time)
	 * <p>
	 *
	 * @param email The recipient mail.
	 * @return Nothing.
	 * @exception Exception It varies from time to time based on the Google Mail Service. Cannot predict exceptions based on their Services.
	 *
	 */

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

