import java.net.URL;
import javax.swing.text.html.parser.*;
import javax.swing.text.html.*;
import javax.swing.text.*;
import java.io.*;
import java.util.*;

public class LinkRetrieve extends HTMLEditorKit.ParserCallback {

	public static void start(String link) throws Exception {

		URL url = new URL(link);

		Reader reader = new InputStreamReader((InputStream) url.getContent());

		new ParserDelegator().parse(reader, new LinkRetrieve(), true);

	}

	@Override
		public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {

			String link = null;

			if (t == HTML.Tag.A) {

				Enumeration<?> attributeNames = a.getAttributeNames();

				if (attributeNames.nextElement().equals(HTML.Attribute.HREF))
					link = a.getAttribute(HTML.Attribute.HREF).toString();

					if (link != null) {
						
						if (link.startsWith("http") && link.contains("://") && !link.contains("://dl.")) {

					try {

						// check if link broken... if not returns "OK"
						if (ServerResponse.response(new URL(link)).equals("OK") && MediaCheck.media(new URL(link))) {

							// Avoid Getting Thrown Out From The Server (finally
							// turn 1 to 2000)
							try {

								Thread.sleep(1);

							} catch (Exception e) {
							}
					}
				}
			}
		}
