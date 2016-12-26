import static org.junit.Assert.*;
import org.junit.Test;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
//import org.junit.TestTimedOutException;
//import org.junit.rules.Timeout;

import java.lang.InterruptedException;

public class MediaCheckTest {
	

	@Rule
	//public Timeout globalTimeout = Timeout.seconds(20);
	public ExpectedException excepion = ExpectedException.none();


	@Test//(timeout=100) 
	public void testMedia()  throws MalformedURLException, InterruptedException{
		
		URL url = new URL("https://tctechcrunch2011.files.wordpress.com/2013/05/house-of-cards-kevin-spacey.jpg");
		assertFalse(MediaCheck.media(url));
		//Thread.sleep(200);
	 
	}
	
}

