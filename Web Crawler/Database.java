import java.sql.*;
import java.net.*;
import java.util.ArrayList;

public class Database {

	Connection dbcon;
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	dbcon = DriverManager.getConnection(“jdbc:odbc:Database”);
	public void InsertData(ArrayList<URL> array, String path) {
	    Statement stmt = dbcon.createStatement();
	    for(int i = 0; i < array.size(); i++) {
	        stmt.executeUpdate("INSERT INTO DatabaseOfURLs"+"VALUES(array.get(i), path + i + '.html')");
		}
	    stmt.close();
	}
	dbcon.close();
}