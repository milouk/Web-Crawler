import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {
public static Connection dbcon;
	public static void InsertData(ArrayList<String> array, String path) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			dbcon = DriverManager.getConnection("jdbc:sqlserver://serverName:1025;databaseName=complet;user=team3;password=2016;");
			Statement stmt = dbcon.createStatement();
			String pathHtml;
			int position;
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
		}
	}
}
