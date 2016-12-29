import static org.junit.Assert.*;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import org.junit.Test;

public class ServerResponseTest {
	

	@Test
	public void testResponse() throws Exception  {
	

		try {
			
			String url = "http://www.google.com/";
			URL obj;
			obj = new URL(url);
	
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		assertEquals(HttpURLConnection.HTTP_OK, con.getResponseCode());
		assertNotNull(url);
		
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		
	}

}


