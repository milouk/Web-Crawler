package com.complet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

/**
 * <h1>Link Retrieve - LinkRetrieve.java</h1> The LinkRetrieve Class is a class
 * that establishes an HTTP connection with a link and then it Extracts all
 * links within an "a" html tag having an HREF attribute.
 * <p>
 * <b>Note: </b>* It also EXCLUDES : <br>
 * css <br>
 * jpg <br>
 * js <br>
 * gif <br>
 * png <br>
 * mp3 <br>
 * mp4 <br>
 * zip <br>
 * gz <br>
 * jpeg <br>
 * pdf <br>
 * Download links ( "://dl." )
 * <p>
 *
 * @author Complet
 * @version 6.0
 * @since 2017-01-02
 */

public class LinkRetrieve extends HTMLEditorKit.ParserCallback {

	public static String baselink1;
	public static String baselink2;
	public static String baselink3;

	// Extensions to EXCLUDE
	private final static Pattern excludes = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp4|zip|gz|jpeg|pdf))$");

	/**
	 * <p>
	 * start is the first method of this class. Gets an String as parameter. it
	 * established an HTTP connection with the link.
	 *
	 * @param link
	 *            which is the link to be checked
	 * @exception IOException
	 *                which occurs when a link is malformed.
	 *                <p>
	 */

	public static void start(String link) {

		// Store base urls of each thread for use in the concatenation in third
		// case where a relative path gets concatenated to the base url.
		if (RunClass.currentThread().getName().equals(Mainclass.getT1name())) {
			baselink1 = link;
		} else if (RunClass.currentThread().getName().equals(Mainclass.getT2name())) {
			baselink2 = link;
		} else if (RunClass.currentThread().getName().equals(Mainclass.getT3name())) {
			baselink3 = link;
		}

		try {

			URL url = new URL(link);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			new ParserDelegator().parse(reader, new LinkRetrieve(), true);

		} catch (IOException e) {

			System.err.println("This URL was malformed --> " + link + " !");
		}

	}

	/**
	 * <p>
	 * handleStartTag is called by the start method. When an "a" tag is met , it
	 * checks if the attribute is HREF. If it is it extracts the link. Depending
	 * on the format of the link ( if it contains a protocol or not, if it is a
	 * path of the base url etc.) and from which thread's base url was this link
	 * extracted. It is then cross-checked if the link is valid or not by
	 * getting the http response from the server it is hosted and if it contains
	 * a meta robot tag. Finally it is added in the thread's list.
	 * <p>
	 *
	 * @param t
	 *            an HTML.Tag.
	 * @param a
	 *            MutableAttributeSet
	 * @param int
	 *            position
	 * @exception NoSuchElementException
	 *                which occurs when an html link does NOT contain at least a
	 *                link with an HREF attribute, hence the enumeration remains
	 *                empty.
	 * @exception IOException
	 *                Input/Output Exception.
	 * @exception InterruptedException
	 *                which occurs when any one of the threads is interrupted.
	 *                <p>
	 */

	@Override
	public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {

		String link = null;

		if (t == HTML.Tag.A) {

			try {

				Enumeration<?> attributeNames = a.getAttributeNames();

				if (attributeNames.nextElement().equals(HTML.Attribute.HREF))

					link = a.getAttribute(HTML.Attribute.HREF).toString();

			} catch (NoSuchElementException e) {

				System.err.println("No HREF Attribute Found in Link ! ");
				return;
			}

			if (link != null) {

				// Exclude links that end in
				// jpeg,jpg,zip,mp,pdf,png,gz,gif,css,gif not contain ://dl.
				// and not javascript:void(0)
				Matcher li = excludes.matcher(link);

				if (!(li.matches() || link.contains("://dl.") || link == ("javascript:void(0)"))) {

					if (link.startsWith("http") && link.contains("://")) {

						try {

							// If thread 1 then
							if (RunClass.currentThread().getName().equals(Mainclass.getT1name())) {

								// Check Link Does NOT already Exist in list
								if (!Mainclass.getThread1_list().contains(link)) {

									// check if link broken... if not returns
									// "OK" and
									// make sure link is not an image
									if (ServerResponse.response(new URL(link)).equals("OK")
											&& MediaCheck.media(new URL(link))) {

										// Checks link's robot's content
										// I only care if it's indexalbe or
										// followable
										// If link is suitable for me i take it
										// !
										// (The noindexables which may stay will
										// be removed after i make use of them)
										RobotTags.checkAccess(link);
										if (RunClass.robotIndex_thread1 == true
												|| RunClass.robotFollow_thread1 == true) {

											Mainclass.getThread1_list().add(link);
											if (RunClass.robotFollow_thread1 == true) {
												RobotTags.thread1_mFollow.add(true);
											} else {
												RobotTags.thread1_mFollow.add(false);
											}
											if (RunClass.robotIndex_thread1 == true) {
												RobotTags.thread1_mIndex.add(true);
											} else {
												RobotTags.thread1_mIndex.add(false);
											}
										}
									}
								}

							} else if (RunClass.currentThread().getName().equals(Mainclass.getT2name())) {
								// Check Link Does NOT already Exist in list
								if (!Mainclass.getThread2_list().contains(link)) {

									// check if link broken... if not returns
									// "OK" and
									// make sure link is not an image
									if (ServerResponse.response(new URL(link)).equals("OK")
											&& MediaCheck.media(new URL(link))) {

										// Checks link's robot's content
										// I only care if it's indexalbe or
										// followable
										// If link is suitable for me i take it
										// !
										// (The noindexables which may stay will
										// be removed after i make use of them)
										RobotTags.checkAccess(link);
										if (RunClass.robotIndex_thread2 == true
												|| RunClass.robotFollow_thread2 == true) {

											Mainclass.getThread2_list().add(link);
											if (RunClass.robotFollow_thread2 == true) {
												RobotTags.thread2_mFollow.add(true);
											} else {
												RobotTags.thread2_mFollow.add(false);
											}
											if (RunClass.robotIndex_thread2 == true) {
												RobotTags.thread2_mIndex.add(true);
											} else {
												RobotTags.thread2_mIndex.add(false);
											}
										}
									}
								}

							} else if (RunClass.currentThread().getName().equals(Mainclass.getT3name())) {
								// Check Link Does NOT already Exist in list
								if (!Mainclass.getThread3_list().contains(link)) {

									// check if link broken... if not returns
									// "OK" and
									// make sure link is not an image
									if (ServerResponse.response(new URL(link)).equals("OK")
											&& MediaCheck.media(new URL(link))) {

										// Checks link's robot's content
										// I only care if it's indexalbe or
										// followable
										// If link is suitable for me i take it
										// !
										// (The noindexables which may stay will
										// be removed after i make use of them)
										RobotTags.checkAccess(link);
										if (RunClass.robotIndex_thread3 == true
												|| RunClass.robotFollow_thread3 == true) {

											Mainclass.getThread3_list().add(link);
											if (RunClass.robotFollow_thread3 == true) {
												RobotTags.thread3_mFollow.add(true);
											} else {
												RobotTags.thread3_mFollow.add(false);
											}
											if (RunClass.robotIndex_thread3 == true) {
												RobotTags.thread3_mIndex.add(true);
											} else {
												RobotTags.thread3_mIndex.add(false);
											}
										}
									}
								}
							}

						} catch (IOException e) {
							System.err.println("Something Went Wrong...Chill");
						}

					} else if (link.startsWith("//")) {

						link = "http:/".concat(link.replaceFirst("//", "/"));

						try {

							// If thread 1 then
							if (RunClass.currentThread().getName().equals(Mainclass.getT1name())) {

								// Check Link Does NOT already Exist in list
								if (!Mainclass.getThread1_list().contains(link)) {

									// check if link broken... if not returns
									// "OK" and
									// make sure link is not an image
									if (ServerResponse.response(new URL(link)).equals("OK")
											&& MediaCheck.media(new URL(link))) {

										// Checks link's robot's content
										// I only care if it's indexalbe or
										// followable
										// If link is suitable for me i take it
										// !
										// (The noindexables which may stay will
										// be removed after i make use of them)
										RobotTags.checkAccess(link);
										if (RunClass.robotIndex_thread1 == true
												|| RunClass.robotFollow_thread1 == true) {

											Mainclass.getThread1_list().add(link);
											if (RunClass.robotFollow_thread1 == true) {
												RobotTags.thread1_mFollow.add(true);
											} else {
												RobotTags.thread1_mFollow.add(false);
											}
											if (RunClass.robotIndex_thread1 == true) {
												RobotTags.thread1_mIndex.add(true);
											} else {
												RobotTags.thread1_mIndex.add(false);
											}
										}
									}
								}

							} else if (RunClass.currentThread().getName().equals(Mainclass.getT2name())) {

								// Check Link Does NOT already Exist in list
								if (!Mainclass.getThread2_list().contains(link)) {

									// check if link broken... if not returns
									// "OK" and
									// make sure link is not an image
									if (ServerResponse.response(new URL(link)).equals("OK")
											&& MediaCheck.media(new URL(link))) {

										// Checks link's robot's content
										// I only care if it's indexalbe or
										// followable
										// If link is suitable for me i take it
										// !
										// (The noindexables which may stay will
										// be removed after i make use of them)
										RobotTags.checkAccess(link);
										if (RunClass.robotIndex_thread2 == true
												|| RunClass.robotFollow_thread2 == true) {

											Mainclass.getThread2_list().add(link);
											if (RunClass.robotFollow_thread2 == true) {
												RobotTags.thread2_mFollow.add(true);
											} else {
												RobotTags.thread2_mFollow.add(false);
											}
											if (RunClass.robotIndex_thread2 == true) {
												RobotTags.thread2_mIndex.add(true);
											} else {
												RobotTags.thread2_mIndex.add(false);
											}
										}
									}
								}

							} else if (RunClass.currentThread().getName().equals(Mainclass.getT3name())) {
								// Check Link Does NOT already Exist in list
								if (!Mainclass.getThread3_list().contains(link)) {

									// check if link broken... if not returns
									// "OK" and
									// make sure link is not an image
									if (ServerResponse.response(new URL(link)).equals("OK")
											&& MediaCheck.media(new URL(link))) {

										// Checks link's robot's content
										// I only care if it's indexalbe or
										// followable
										// If link is suitable for me i take it
										// !
										// (The noindexables which may stay will
										// be removed after i make use of them)
										RobotTags.checkAccess(link);
										if (RunClass.robotIndex_thread3 == true
												|| RunClass.robotFollow_thread3 == true) {

											Mainclass.getThread3_list().add(link);
											if (RunClass.robotFollow_thread3 == true) {
												RobotTags.thread3_mFollow.add(true);
											} else {
												RobotTags.thread3_mFollow.add(false);
											}
											if (RunClass.robotIndex_thread3 == true) {
												RobotTags.thread3_mIndex.add(true);
											} else {
												RobotTags.thread3_mIndex.add(false);
											}
										}
									}
								}
							}

						} catch (IOException e) {
							System.err.println("Something Went Wrong...Chill");
						}

						// if it does not start with an '/' then it is NOT a
						// path of the root link.
					} else if (link.startsWith("/")) {
						try {

							// If thread 1
							if (RunClass.currentThread().getName().equals(Mainclass.getT1name())) {

								// Concatenates path with root link the correct
								// way, Mainclas.Link1 is url currently crawled
								link = new URL(new URL(baselink1), link).toString();

								// Check Link Does NOT already Exist in list
								if (!Mainclass.getThread1_list().contains(link)) {

									// check if link broken... if not returns
									// "OK" and
									// make sure link is not an image
									if (ServerResponse.response(new URL(link)).equals("OK")
											&& MediaCheck.media(new URL(link))) {

										// Checks link's robot's content
										// I only care if it's indexalbe or
										// followable
										// If link is suitable for me i take it
										// !
										// (The noindexables which may stay will
										// be removed after i make use of them)
										RobotTags.checkAccess(link);
										if (RunClass.robotIndex_thread1 == true
												|| RunClass.robotFollow_thread1 == true) {

											Mainclass.getThread1_list().add(link);
											if (RunClass.robotFollow_thread1 == true) {
												RobotTags.thread1_mFollow.add(true);
											} else {
												RobotTags.thread1_mFollow.add(false);
											}
											if (RunClass.robotIndex_thread1 == true) {
												RobotTags.thread1_mIndex.add(true);
											} else {
												RobotTags.thread1_mIndex.add(false);
											}
										}
									}
								}

								// if thread 2
							} else if (RunClass.currentThread().getName().equals(Mainclass.getT2name())) {

								// Concatenates path with root link the correct
								// way
								link = new URL(new URL(baselink2), link).toString();

								// Check Link Does NOT already Exist in list
								if (!Mainclass.getThread2_list().contains(link)) {

									// check if link broken... if not returns
									// "OK" and
									// make sure link is not an image
									if (ServerResponse.response(new URL(link)).equals("OK")
											&& MediaCheck.media(new URL(link))) {

										// Checks link's robot's content
										// I only care if it's indexalbe or
										// followable
										// If link is suitable for me i take it
										// !
										// (The noindexables which may stay will
										// be removed after i make use of them)
										RobotTags.checkAccess(link);
										if (RunClass.robotIndex_thread2 == true
												|| RunClass.robotFollow_thread2 == true) {

											Mainclass.getThread2_list().add(link);
											if (RunClass.robotFollow_thread2 == true) {
												RobotTags.thread2_mFollow.add(true);
											} else {
												RobotTags.thread2_mFollow.add(false);
											}
											if (RunClass.robotIndex_thread2 == true) {
												RobotTags.thread2_mIndex.add(true);
											} else {
												RobotTags.thread2_mIndex.add(false);
											}
										}
									}
								}

								// if thread 3
							} else if (RunClass.currentThread().getName().equals(Mainclass.getT3name())) {

								// Concatenates path with root link the correct
								// way
								link = new URL(new URL(baselink3), link).toString();

								// Check Link Does NOT already Exist in list
								if (!Mainclass.getThread3_list().contains(link)) {

									// Checks link's robot's content
									// I only care if it's indexalbe or
									// followable
									// If link is suitable for me i take it !
									// (The noindexables which may stay will be
									// removed after i make use of them)
									RobotTags.checkAccess(link);
									if (RunClass.robotIndex_thread3 == true || RunClass.robotFollow_thread3 == true) {

										Mainclass.getThread3_list().add(link);
										if (RunClass.robotFollow_thread3 == true) {
											RobotTags.thread3_mFollow.add(true);
										} else {
											RobotTags.thread3_mFollow.add(false);
										}
										if (RunClass.robotIndex_thread3 == true) {
											RobotTags.thread3_mIndex.add(true);
										} else {
											RobotTags.thread3_mIndex.add(false);
										}
									}
								}

							}

						} catch (IOException e) {
							System.err.println("Just handled an exception..dont worry!");
						}

						// Sleep to Avoid Getting Thrown Out From The Server
						try {
							Thread.sleep(1500);
						} catch (InterruptedException e) {
							System.err.println("Oups Something Interrupted The Thread ...");
						}
					}
				}
			}
		}
	}
}