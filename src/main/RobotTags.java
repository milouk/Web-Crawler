package com.complet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

/**
 * <h1>Robot meta tags - RobotTags.java</h1> The RobotTags Class establishes an
 * HTTP connection with a link and checks its meta tag content.
 *
 * Its main purpose is to check if the link has a meta tag with an attribute
 * NAME that is "robots" and give instructions to the program on how to manage
 * this link depending on its attribute CONTENT
 * <p>
 * <b>Note: </b> RobotTags also has an extra feature which is used in the end of
 * the program: <br>
 * remover() method <br>
 * <p>
 *
 * @author Complet
 * @version 6.0
 * @since 2016-12-29
 *
 */

public class RobotTags extends HTMLEditorKit.ParserCallback {

	// These lists tell the crawler if a link is indexable or
	// followable
	// They get their values at LinkRetrieve
	// By true or false they tell if LinkRetrieve should run
	// Also they are used by remover, they help telling which links need to
	// be removed by true or false
	public static ArrayList<Boolean> thread1_mIndex = new ArrayList<Boolean>();
	public static ArrayList<Boolean> thread1_mFollow = new ArrayList<Boolean>();

	public static ArrayList<Boolean> thread2_mIndex = new ArrayList<Boolean>();
	public static ArrayList<Boolean> thread2_mFollow = new ArrayList<Boolean>();

	public static ArrayList<Boolean> thread3_mIndex = new ArrayList<Boolean>();
	public static ArrayList<Boolean> thread3_mFollow = new ArrayList<Boolean>();

	public static ArrayList<Boolean> mIndex = new ArrayList<Boolean>();

	/**
	 * <p>
	 * remover is the first method of this class. It removes all the links from
	 * the finalist that are noindexable and got retrieved because they were
	 * followable.
	 * <p>
	 */

	public static void remover() {

		for (int i = 0; i < Mainclass.getFinalist().size(); i++) {
			if (mIndex.get(i) == false) {
				Mainclass.getFinalist().remove(i);
			}
		}
	}

	/**
	 * <p>
	 * checkAccess is the second method of this class. It established an HTTP
	 * connection with the link and turns all the variables into true to be
	 * ready for the robot tags check session.
	 *
	 * @param link
	 *            which is the link to be checked
	 * @exception IOException
	 *                which occurs when a link is malformed.
	 *                <p>
	 */

	public static void checkAccess(String link) {

		if (RunClass.currentThread().getName().equals(Mainclass.getT1name())) {
			RunClass.robotFollow_thread1 = true;
			RunClass.robotIndex_thread1 = true;
		} else if (RunClass.currentThread().getName().equals(Mainclass.getT2name())) {
			RunClass.robotFollow_thread2 = true;
			RunClass.robotIndex_thread2 = true;
		} else if (RunClass.currentThread().getName().equals(Mainclass.getT3name())) {
			RunClass.robotFollow_thread3 = true;
			RunClass.robotIndex_thread3 = true;
		}

		try {

			URL url = new URL(link);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			new ParserDelegator().parse(reader, new RobotTags(), true);
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	/**
	 * <p>
	 * handleSimpleTag is called by the checkAccess method. When a "t" tag is
	 * met , it checks if the attribute NAME is robots. If it is then it checks
	 * the attribute CONTENT. It only checks if the attribute CONTENT is
	 * "follow", "nofollow", "index" or "noindex". If the CONTENT has "nofollow"
	 * it turns the right variable into false to tell to LinkRetrieve to not
	 * retrieve the links that are in the link it currently is, if not OR has
	 * "follow", the variable will stay true and it means that LinkRetrieve can
	 * retrieve them.
	 *
	 * If the CONTENT has "noindex" it turns the right variable into false to
	 * tell to LinkRetrieve to not add the link it currently is in the list, if
	 * not OR has "index", the variable will stay true and it means that
	 * LinkRetrieve can add it.
	 *
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
	public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos) {

		if (t == HTML.Tag.META) {

			String name = (String) a.getAttribute(HTML.Attribute.NAME);
			String content = (String) a.getAttribute(HTML.Attribute.CONTENT);

			if (name != null && (name.contains("robots") || name.contains("ROBOTS"))) {
				if (content != null && (content.contains("nofollow") || content.contains("NOFOLLOW"))) {
					if (RunClass.currentThread().getName().equals(Mainclass.getT1name())) {
						RunClass.robotFollow_thread1 = false;
					} else if (RunClass.currentThread().getName().equals(Mainclass.getT2name())) {
						RunClass.robotFollow_thread2 = false;
					} else if (RunClass.currentThread().getName().equals(Mainclass.getT3name())) {
						RunClass.robotFollow_thread3 = false;
					}
				}
				if (content != null && (content.contains("noindex") || content.contains("NOINDEX"))) {
					if (RunClass.currentThread().getName().equals(Mainclass.getT1name())) {
						RunClass.robotIndex_thread1 = false;
					} else if (RunClass.currentThread().getName().equals(Mainclass.getT2name())) {
						RunClass.robotIndex_thread2 = false;
					} else if (RunClass.currentThread().getName().equals(Mainclass.getT3name())) {
						RunClass.robotIndex_thread3 = false;
					}
				}
			}
		}
	}
}