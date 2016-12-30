package com.complet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * This class consists exclusively of static methods that
 * operate on a database. It contains methods that connect
 * to the database, disconnect from it and update it.
 *
 * The methods of this class all throw a SQLException in
 * case something goes wrong between the database-java communication.
 */

public class DatabaseConnection {
	public static Connection dbcon;
	public static Statement stmt;

	/**
	 * Creates a connection to a database.
	 *
	 * @throws Exception if the registration to the JDBC driver fails.
	 * @throws SQLException if the connection to the database fails.
	 */

	public static void openDatabaseConnection() {
		try {
			GetCredentials.dbCredentials("path DataBase-Credentials.txt"); //Calls the dbCredentials method
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //Registers to the JDBC driver
			dbcon = DriverManager.getConnection(
					"jdbc:sqlserver://".concat(GetCredentials.getSqlServerPath()).concat(";databaseName=")
							.concat(GetCredentials.getDbName()).concat(";user=").concat(GetCredentials.getDbUser())
							.concat(";password=").concat(GetCredentials.getDbPassword()).concat(";"));
			stmt = dbcon.createStatement(); //Creates a statement that represents a SQL statement
		} catch (SQLException sqle) {
								//Handles errors for JDBC
			System.err.println(sqle);
		} catch (Exception e) {
								//Handles errors for Class.forName
			System.err.println(e);
		}
	}

	/**
	 * Closes the open connection to the database.
	 *
	 * @throws Exception
	 * @throws SQLException if there is a problem disconnecting from the database.
	 */

	public static void closeDatabaseConnection() {
		try {
			openDatabaseConnection(); //Calls the openDatabaseConnection method to ensure there is an open connection
			stmt.close(); //Closes the Statement object
			dbcon.close(); //Closes the Connection object
		} catch (SQLException sqle) {
								//Handles errors for JDBC
			System.err.println(sqle);
		} catch (Exception e) {
			System.err.println(e);
		} finally {
							//Makes sure there is no leak in resources
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException sqle2) {
				System.err.println(sqle2);
			}
			try {
				if (dbcon != null) {
					dbcon.close();
				}
			} catch (SQLException sqle) {
				System.err.println(sqle);
			}
		}
	}

	/**
	 * Inserts data into the database.
	 * The array argument specifies an ArrayList that contains URLs.
	 * The path argument is a specifier for a file that contains HTML text.
	 *
	 * @array an ArrayList that contains the URLs to be inserted in the database in form of Strings.
     * @param path a path that points to the file where the HTML text of the links is saved.
     * @throws SQLException if an error occurs during the data insertion.
     */

	public static void InsertData(String links, String path) {
		try {
			String pathHtml;

			openDatabaseConnection(); //Creates a connection to the database
			pathHtml = path + "/" + Integer.toString(Mainclass.getPosition()) + ".html"; //Specifies the path of each HTML
			stmt.executeUpdate("INSERT INTO DatabaseOfURLs VALUES('" + Mainclass.getPosition() + "','" + links + "','"
					+ pathHtml + "')"); //Inserts data into the database
			closeDatabaseConnection(); //Closes the connection to the database

		} catch (SQLException sqle) {
			System.err.println(sqle);
		}
	}

	/**
	 * Deletes all the columns from a database table.
	 * The tbName specifies a table's name which already exists in the database.
	 *
	 * @tbName the name of a table in the database which columns will be deleted
	 * @throws SQLException if an error occurs during the data deletion
     */

	public static void deleteData(String dbName) {
		try {
			openDatabaseConnection(); //Creates a connection to the database
			stmt.executeUpdate("DELETE FROM " + dbName + ";"); //Deletes data from the dbName table
			closeDatabaseConnection(); //Closes the connection to the database
		} catch (SQLException e) {
							//Handles errors for JDBC
			System.err.println(e);
		}
    }
}