package com.complet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseConnection {
    public static Connection dbcon;
    public static Statement stmt;
    public static void openDatabaseConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			dbcon = DriverManager.getConnection(
					"jdbc:sqlserver://".concat(GetCredentials.getSqlServerPath()).concat(";databaseName=")
					.concat(GetCredentials.getDbName()).concat(";user=").concat(GetCredentials.getDbUser())
					.concat(";password=").concat(GetCredentials.getDbPassword()).concat(";"));
		    stmt = dbcon.createStatement();
		 } catch (SQLException sqle) {
			System.err.println(sqle);
		 } catch (Exception e) {
			System.err.println(e);
		 }
	}
	public static void InsertData(ArrayList<String> array, String path) {
		try {
			String pathHtml;
			int position;
			openDatabaseConnection();
		    for(int i = 0; i < array.size(); i++) {
		        position = i;
				pathHtml = path + "/" + Integer.toString(i) + ".html";
				stmt.executeUpdate("INSERT INTO DatabaseOfURLs VALUES('" + position + "','" + array.get(i) + "','" + pathHtml + "')");
            }
    		stmt.close();
		    dbcon.close();

        } catch (SQLException sqle) {
			System.err.println(sqle);
		} catch (Exception e) {
			System.err.println(e);
		} finally {
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
}