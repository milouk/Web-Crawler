package com.complet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GetCredentials {
	private static String email;
	private static String emailPassword;
	private static String sqlServerPath;
	private static String dbUser;
	private static String dbPassword;
	private static String dbName;

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

	public static String getEmail() {
		return email;
	}

	public static String getEmailPassword() {
		return emailPassword;
	}

	public static String getSqlServerPath() {
		return sqlServerPath;
	}

	public static String getDbUser() {
		return dbUser;
	}

	public static String getDbPassword() {
		return dbPassword;
	}

	public static String getDbName() {
		return dbName;
	}

}