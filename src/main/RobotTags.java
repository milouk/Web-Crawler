package com.complet;

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

public class RobotTags extends HTMLEditorKit.ParserCallback {

    //These lists are telling to the crawler if a link is indexable or followable
    //They get their values at LinkRetrieve
    //By true or false they tell if the linkRetrieve should run
    //Also they are used to the remover, they help telling which links need to be removed by true or false
	public static ArrayList<Boolean> thread1_mIndex = new ArrayList<Boolean>();
	public static ArrayList<Boolean> thread1_mFollow = new ArrayList<Boolean>();

	public static ArrayList<Boolean> thread2_mIndex = new ArrayList<Boolean>();
	public static ArrayList<Boolean> thread2_mFollow = new ArrayList<Boolean>();

	public static ArrayList<Boolean> thread3_mIndex = new ArrayList<Boolean>();
	public static ArrayList<Boolean> thread3_mFollow = new ArrayList<Boolean>();

	public static ArrayList<Boolean> mIndex = new ArrayList<Boolean>();

	public static int counter = 0;//Counts how many noindex links need to be removed

	//Removes the noindexables from the list when called
	public static void remover() {



		for (int i = 0; i < Mainclass.getFinalist().size(); i++) {

			if (mIndex.get(i) == false) {
				Mainclass.getFinalist().remove(i);
			}
		}
	}

    //Checks if i should keep the link depending of the robot's content
	public static void checkAccess(String link) {

        if (RunClass.currentThread().getName().equals(Mainclass.getT1name())) {

			RunClass.robotFollow_thread1 = true;
			RunClass.robotIndex_thread1 = true;

		}else if (RunClass.currentThread().getName().equals(Mainclass.getT2name())) {

			RunClass.robotFollow_thread2 = true;
			RunClass.robotIndex_thread2 = true;

		}else if (RunClass.currentThread().getName().equals(Mainclass.getT3name())) {

			RunClass.robotFollow_thread3 = true;
			RunClass.robotIndex_thread3 = true;

		}

		try {



            URL url = new URL(link);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			new ParserDelegator().parse(reader, new RobotTags(), true);

		} catch (Exception e) {
		}

	}

	//Checks the robot's contents (indexable or followable)
	@Override
			public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos) {

				if (t == HTML.Tag.META) {

					String name = (String) a.getAttribute(HTML.Attribute.NAME);
					String content = (String) a.getAttribute(HTML.Attribute.CONTENT);

					if (name != null
							&& (name.contains("robots") || name.contains("ROBOTS"))) {

						System.out.println("META name: " + name);
						System.out.println("META content: " + content + "\n");



							if (content != null
									&& (content.contains("nofollow") || content
											.contains("NOFOLLOW"))) {

								if (RunClass.currentThread().getName()
										.equals(Mainclass.getT1name())) {


									RunClass.robotFollow_thread1 = false;

								} else if (RunClass.currentThread().getName()
										.equals(Mainclass.getT2name())) {


									RunClass.robotFollow_thread2 = false;

								} else if (RunClass.currentThread().getName()
										.equals(Mainclass.getT3name())) {


									RunClass.robotFollow_thread3 = false;
								}

							}

							if (content != null
									&& (content.contains("noindex") || content
											.contains("NOINDEX"))) {


								if (RunClass.currentThread().getName()
										.equals(Mainclass.getT1name())) {


									RunClass.robotIndex_thread1 = false;

								} else if (RunClass.currentThread().getName()
										.equals(Mainclass.getT2name())) {


									RunClass.robotIndex_thread2 = false;

								} else if (RunClass.currentThread().getName()
										.equals(Mainclass.getT3name())) {


									RunClass.robotIndex_thread3 = false;
								}
							}


					}

				}
			}
}