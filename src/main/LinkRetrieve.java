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

public class LinkRetrieve extends HTMLEditorKit.ParserCallback {

	// Extensions to EXCLUDE
	private final static Pattern excludes = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp4|zip|gz|jpeg|pdf))$");

	public static void start(String link) {

		try {

			URL url = new URL(link);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			new ParserDelegator().parse(reader, new LinkRetrieve(), true);

		} catch (IOException e) {

			System.err.println("This URL was malformed --> " + link + " !");
		}

	}

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

                                        //Checks link's robot's content
                                        //I only care if it's indexalbe or followable
                                        //If link is suitable for me i take it !
                                        //(The noindexables which may stay will be removed after i make use of them)
                                        RobotTags.checkAccess(link);
                                        if (RunClass.robotIndex_thread1 == true || RunClass.robotFollow_thread1 == true) {

										    Mainclass.getThread1_list().add(link);
										    if (RunClass.robotFollow_thread1 == true) {
												RobotTags.thread1_mFollow.add(true);
											} else {
												RobotTags.thread1_mFollow.add(false);
											}
											if (RunClass.robotIndex_thread1 == true) {
												RobotTags.thread1_mIndex.add(true);
											} else {
												RobotTags.counter++;
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


										//Checks link's robot's content
									    //I only care if it's indexalbe or followable
										//If link is suitable for me i take it !
                                        //(The noindexables which may stay will be removed after i make use of them)
										RobotTags.checkAccess(link);
										if (RunClass.robotIndex_thread2 == true || RunClass.robotFollow_thread2 == true) {

											Mainclass.getThread2_list().add(link);
											if (RunClass.robotFollow_thread2 == true) {
												RobotTags.thread2_mFollow.add(true);
											} else {
												RobotTags.thread2_mFollow.add(false);
											}
											if (RunClass.robotIndex_thread2 == true) {
												RobotTags.thread2_mIndex.add(true);
											} else {
												RobotTags.counter++;
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

										//Checks link's robot's content
										//I only care if it's indexalbe or followable
										//If link is suitable for me i take it !
										//(The noindexables which may stay will be removed after i make use of them)
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
												RobotTags.counter++;
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

										//Checks link's robot's content
										//I only care if it's indexalbe or followable
										//If link is suitable for me i take it !
										//(The noindexables which may stay will be removed after i make use of them)
										RobotTags.checkAccess(link);
										if (RunClass.robotIndex_thread1 == true || RunClass.robotFollow_thread1 == true) {

											Mainclass.getThread1_list().add(link);
											if (RunClass.robotFollow_thread1 == true) {
												RobotTags.thread1_mFollow.add(true);
											} else {
												RobotTags.thread1_mFollow.add(false);
											}
											if (RunClass.robotIndex_thread1 == true) {
												RobotTags.thread1_mIndex.add(true);
											} else {
												RobotTags.counter++;
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

										//Checks link's robot's content
										//I only care if it's indexalbe or followable
										//If link is suitable for me i take it !
										//(The noindexables which may stay will be removed after i make use of them)
										RobotTags.checkAccess(link);
										if (RunClass.robotIndex_thread2 == true || RunClass.robotFollow_thread2 == true) {

											Mainclass.getThread2_list().add(link);
											if (RunClass.robotFollow_thread2 == true) {
												RobotTags.thread2_mFollow.add(true);
											} else {
												RobotTags.thread2_mFollow.add(false);
											}
											if (RunClass.robotIndex_thread2 == true) {
												RobotTags.thread2_mIndex.add(true);
											} else {
												RobotTags.counter++;
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

										//Checks link's robot's content
										//I only care if it's indexalbe or followable
										//If link is suitable for me i take it !
										//(The noindexables which may stay will be removed after i make use of them)
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
												RobotTags.counter++;
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
								// way
								link = new URL(new URL(Mainclass.getLink1()), link).toString();

								// Check Link Does NOT already Exist in list
								if (!Mainclass.getThread1_list().contains(link)) {

									// check if link broken... if not returns
									// "OK" and
									// make sure link is not an image
									if (ServerResponse.response(new URL(link)).equals("OK")
											&& MediaCheck.media(new URL(link))) {

										//Checks link's robot's content
										//I only care if it's indexalbe or followable
										//If link is suitable for me i take it !
										//(The noindexables which may stay will be removed after i make use of them)
										RobotTags.checkAccess(link);
										if (RunClass.robotIndex_thread1 == true || RunClass.robotFollow_thread1 == true) {

											Mainclass.getThread1_list().add(link);
											if (RunClass.robotFollow_thread1 == true) {
												RobotTags.thread1_mFollow.add(true);
											} else {
												RobotTags.thread1_mFollow.add(false);
											}
											if (RunClass.robotIndex_thread1 == true) {
												RobotTags.thread1_mIndex.add(true);
											} else {
												RobotTags.counter++;
												RobotTags.thread1_mIndex.add(false);
											}


										}
									}
								}

								// if thread 2
							} else if (RunClass.currentThread().getName().equals(Mainclass.getT2name())) {

								// Concatenates path with root link the correct
								// way
								link = new URL(new URL(Mainclass.getLink2()), link).toString();

								// Check Link Does NOT already Exist in list
								if (!Mainclass.getThread2_list().contains(link)) {

									// check if link broken... if not returns
									// "OK" and
									// make sure link is not an image
									if (ServerResponse.response(new URL(link)).equals("OK")
											&& MediaCheck.media(new URL(link))) {

										//Checks link's robot's content
										//I only care if it's indexalbe or followable
										//If link is suitable for me i take it !
										//(The noindexables which may stay will be removed after i make use of them)
										RobotTags.checkAccess(link);
										if (RunClass.robotIndex_thread2 == true || RunClass.robotFollow_thread2 == true) {

											Mainclass.getThread2_list().add(link);
											if (RunClass.robotFollow_thread2 == true) {
												RobotTags.thread2_mFollow.add(true);
											} else {
												RobotTags.thread2_mFollow.add(false);
											}
											if (RunClass.robotIndex_thread2 == true) {
												RobotTags.thread2_mIndex.add(true);
											} else {
												RobotTags.counter++;
												RobotTags.thread2_mIndex.add(false);
											}



										}
									}
								}

								// if thread 3
							} else if (RunClass.currentThread().getName().equals(Mainclass.getT3name())) {

								// Concatenates path with root link the correct
								// way
								link = new URL(new URL(Mainclass.getLink3()), link).toString();

								// Check Link Does NOT already Exist in list
								if (!Mainclass.getThread3_list().contains(link)) {

									// check if link broken... if not returns
									// "OK" and
									// make sure link is not an image
									if (ServerResponse.response(new URL(link)).equals("OK")
											&& MediaCheck.media(new URL(link))) {

										//Checks link's robot's content
										//I only care if it's indexalbe or followable
										//If link is suitable for me i take it !
										//(The noindexables which may stay will be removed after i make use of them)
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
												RobotTags.counter++;
												RobotTags.thread3_mIndex.add(false);
											}


										}
									}
								}

							}

						} catch (IOException e) {

							System.err.println("Just handled an exception..dont worry!");
						}

						// Avoid Getting Thrown Out From The Server

						try {

							Thread.sleep(1);

						} catch (Exception e) {

							System.err.println("Oups Something Interrupted The Thread ...");
						}

					}

				}
			}
		}
	}
}











