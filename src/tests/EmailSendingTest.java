import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.NoSuchProviderException;

import java.lang.SecurityException;

import java.io.IOException;

import java.util.Properties;


public class EmailSendingTest {
	

	private EmailSending mailSender;
	
	Authenticator PasswordAuthentication;
	
	Properties props =System.getProperties();
 
	
	@Before
	 public void setUp() {
	  mailSender = new EmailSending();
	  
	  props.put("mail.smtp.auth", "false");
	  props.put("mail.smtp.starttls.enable", "true");
	  props.put("mail.smtp.host", "host");
	  props.put("mail.smtp.port", "25");

	 }
	
	 @Test 
	 public void testEmail()  throws MessagingException, IOException {

	  String subject = "Test1";
	  String body = "Test Message1";
	  EmailSending.email("joannaschiza13@gmail.com");
	  try {
		  
		
		Session session = Session.getInstance(props);

          try {
        	  Store store = session.getStore("smtp");
        	  
        	  store.connect("gmail.com", "joannaschiza13", "password");
        	  Folder folder = store.getFolder("INBOX");
        	  folder.open(Folder.READ_ONLY);
		      Message[] msg = folder.getMessages();
		  
		      assertTrue(msg.length == 1);
		      assertEquals(subject, msg[0].getSubject());
		      assertEquals(body, msg[0].getContent());
		      assertNotNull(body);
		      folder.close(true);
		      store.close();
		      
          } catch (NoSuchProviderException nspe) {
        	  System.err.println(nspe.getMessage());
          }
          
	   } catch (SecurityException se) {
		  System.err.println("Could not write to file " + se.getMessage());
	  }
    }
}
