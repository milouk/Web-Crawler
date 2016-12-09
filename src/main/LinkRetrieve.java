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
					}
				}
			}
		}
