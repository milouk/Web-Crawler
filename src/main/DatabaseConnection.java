import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {
public static Connection dbcon;
	public static void InsertData(ArrayList<String> array, String path) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			dbcon = DriverManager.getConnection("jdbc:sqlserver://serverName:1025;databaseName=complet;user=team3;password=2016;");
		    dbcon.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
