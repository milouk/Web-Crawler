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

					    if (!(link.endsWith(".jpeg") || link.endsWith(".jpg") || link.endsWith(".zip") || link.endsWith(".tar")
						|| link.endsWith(".mp") || link.contains("://dl."))) {

					if (link.startsWith("http") && link.contains("://")) {

						try {

							// check if link broken... if not returns "OK"
							if (ServerResponse.response(new URL(link)).equals("OK")
									&& MediaCheck.media(new URL(link))) {

								// If thread 1 then
								if (RunClass.currentThread().getName().equals(Mainclass.getT1name())) {

									Mainclass.getThread1_list().add(link);

									// if thread 2 then
								} else if (RunClass.currentThread().getName().equals(Mainclass.getT2name())) {

									Mainclass.getThread2_list().add(link);

									// if thread 3 then
								} else if (RunClass.currentThread().getName().equals(Mainclass.getT3name())) {

									Mainclass.getThread3_list().add(link);
								}
