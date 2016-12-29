package com.complet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

public class LinkRetrieve extends HTMLEditorKit.ParserCallback {

	// Extensions to EXCLUDE
    private final static Pattern excludes = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp4|zip|gz|jpeg|pdf))$");

	public static void start(String link)  {

		try {
			URL url= new URL(link);


            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(new InputStreamReader(connection.getInputStream()));

             new ParserDelegator().parse(reader, new LinkRetrieve(), true);

	    } catch (IOException e) {

			System.err.println("This URL was malformed --> " + link + " !");
	    }

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
						|| link.endsWith(".mp") || link.endsWith(".pdf") || link.contains("://dl."))) {


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

					      } catch (IOException e) {

					          System.err.println("Something Went Wrong...Chill");
					      }

				    } else if (link.startsWith("//")) {

						link = "https:/" + link.replaceFirst("//", "/");

						try {

							if (ServerResponse.response(new URL(link)).equals("OK")
									&& MediaCheck.media(new URL(link))) {

								// If thread 1 then
								if (RunClass.currentThread().getName().equals(Mainclass.getT1name())) {

									Mainclass.getThread1_list().add(link);

									// If thread 1 then
								} else if (RunClass.currentThread().getName().equals(Mainclass.getT2name())) {

									Mainclass.getThread2_list().add(link);

									// if thread 3 then
								} else if (RunClass.currentThread().getName().equals(Mainclass.getT3name())) {

									Mainclass.getThread3_list().add(link);
								}
				             }
					     } catch (IOException e) {

							 System.err.println("Something Went Wrong...Chill");
					     }

                        } else {

							// If thread 1 root link concatenate
						    if (RunClass.currentThread().getName().equals(Mainclass.getT1name())) {

								link = Mainclass.getLink1() + link.replaceFirst("//", "/");

							    // if thread 2 root link concatenate
							} else if (RunClass.currentThread().getName().equals(Mainclass.getT2name())) {

								    link = Mainclass.getLink2() + link.replaceFirst("//", "/");

									// if thread 3 root link concatenate
						    } else if (RunClass.currentThread().getName().equals(Mainclass.getT3name())) {

							    link = Mainclass.getLink3() + link.replaceFirst("//", "/");
						    }

						    try {

								if (ServerResponse.response(new URL(link)).equals("OK")
										&& MediaCheck.media(new URL(link))) {

									// If thread 1 then
									if (RunClass.currentThread().getName().equals(Mainclass.getT1name())) {

										Mainclass.getThread1_list().add(link);

								        // If thread 1 then
								    } else if (RunClass.currentThread().getName().equals(Mainclass.getT2name())) {

										Mainclass.getThread2_list().add(link);

										// if thread 3 then
									} else if (RunClass.currentThread().getName().equals(Mainclass.getT3name())) {

									    Mainclass.getThread3_list().add(link);

									}

							     }

						     } catch (IOException e) {

								 System.err.println("Something Went Wrong...Chill");
						     }

					     }
					  	}
					  			}

					  			// Avoid Getting Thrown Out From The Server

					  			try {

					  				Thread.sleep(600);

					  			} catch (Exception e) {
					  			}

					  		}

					  	}

}











