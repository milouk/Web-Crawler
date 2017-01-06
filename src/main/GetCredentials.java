package com.complet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * <h1>Get the Credentials - GetCredentials.java</h1> The GetCredentials class
 * simply allows us to read from a file and draw what we need on each occasion.
 * <p>
 * <b>Note: </b>Must get a valid File in order to execute the process,
 * otherwise, it will not work.
 *
 * @author Complet
 * @version 6.0
 * @since 2016-12-30
 */

public class GetCredentials {

	private static String email;
	private static String emailPassword;
	private static String sqlServerPath;
	private static String dbUser;
	private static String dbPassword;
	private static String dbName;

	/**
	 * <p>
	 * EmailCredentials is the first method of this class. After getting a file
	 * as a parameter, it checks if the file exists.
	 * <p>
	 * If so, it reads every line of the file and searches for specific Cases.
	 * <b>EmailCredentials must find:</b><br>
	 * <li>An E-mail<br>
	 * <li>A Password<br>
	 * They will be used for the EmailSending class, as part of the host
	 * settings needed.
	 * <p>
	 *
	 * @param file
	 *            A file that is not corrupted and contains characters.
	 * @return Nothing.
	 * @exception IOException
	 *                Input/Output Exception
	 *
	 */

	public static void emailCredentials(String file) {

		try {
			if (new File(file).exists()) {

				BufferedReader fr = new BufferedReader(new FileReader(file));
				String input;
				String[] credentials;

				while ((input = fr.readLine()) != null) {
					credentials = input.split(": ");
					switch (credentials[0]) {
					case "Email":
						email = credentials[1];
						break;
					case "Password":
						emailPassword = credentials[1];
						break;

					}
				}

				fr.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * DbCredentials is the Second method of this class. After getting a file as
	 * a parameter, it checks if the file exists.
	 * <p>
	 * If so, it reads every line of the file and searches for specific Cases.
	 * <b>dbCredentials must find: </b><br>
	 * <li>Sql Server Name<br>
	 * <li>Database Name<br>
	 * <li>UserName<br>
	 * <li>Password<br>
	 * They will be used to connect our program with the Database.
	 * <p>
	 *
	 * @param file
	 *            A file that is not corrupted and contains characters.
	 * @return Nothing.
	 * @exception IOException
	 *                Input/Output Exception
	 *
	 */

	public static void dbCredentials(String file) {

		try {
			if (new File(file).exists()) {

				BufferedReader fr = new BufferedReader(new FileReader(file));
				String input;
				String[] credentials;

				while ((input = fr.readLine()) != null) {
					credentials = input.split(": ");

					switch (credentials[0]) {
					case "sqlserver":
						sqlServerPath = credentials[1];
						break;
					case "databaseName":
						dbName = credentials[1];
						break;
					case "user":
						dbUser = credentials[1];
						break;
					case "password":
						dbPassword = credentials[1];
						break;

					}
				}

				fr.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return email Private variable email (Changes based on the content of the
	 *         file, emailCredentials method).
	 */

	public static String getEmail() {
		return email;
	}

	/**
	 * @return emailPassword Private variable emailPassowrd (Changes based on
	 *         the content of the file, emailCredentials method).
	 */

	public static String getEmailPassword() {
		return emailPassword;
	}

	/**
	 * @return sqlServerPath Private variable sqlServerPath (Changes based on
	 *         the content of the file, dbCredentials method).
	 */

	public static String getSqlServerPath() {
		return sqlServerPath;
	}

	/**
	 * @return dbUser Private variable dbUser (Changes based on the content of
	 *         the file, dbCredentials method).
	 */

	public static String getDbUser() {
		return dbUser;
	}

	/**
	 * @return dbPassword Private variable dbPassword (Changes based on the
	 *         content of the file,dbCredentials method).
	 */

	public static String getDbPassword() {
		return dbPassword;
	}

	/**
	 * @return dbName Private variable dbName (Changes based on the content of
	 *         the file,dbCredentials method).
	 */

	public static String getDbName() {
		return dbName;
	}

}
