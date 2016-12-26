import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


import org.junit.Test;

public class DatabaseTest {

	Connection connection;
	

	@Test
	public void testInsertData() throws SQLException, Exception {
		
        Database.InsertData(Mainclass.getFinalist(), "C:\\Users\\ΘΑΝΑΣΗΣ\\.eclipse");
        if (connection != null) {
        	Statement statement = connection.createStatement();
        	assertTrue(statement.isClosed());
            assertNull("Error occured", statement);
        }
        
	}

}
