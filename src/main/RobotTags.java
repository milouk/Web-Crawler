package com.complet;

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

	public static void checkAccess(String link) throws Exception {

		URL url = new URL(link);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		BufferedReader reader = new BufferedReader(new InputStreamReader((InputStream) connection.getInputStream()));

		new ParserDelegator().parse(reader, new RobotTags(), true);

	}

	@Override
	public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos) {

		if (t == HTML.Tag.META) {

			String content = (String) a.getAttribute(HTML.Attribute.CONTENT);

			if (content != null && content.contains("index")) {

				if (content.contains("nofollow")) {

					if (RunClass.currentThread().getName().equals(Mainclass.getT1name())) {

						RunClass.robot_thread1 = false;

					} else if (RunClass.currentThread().getName().equals(Mainclass.getT2name())) {

						RunClass.robot_thread2 = false;

					} else if (RunClass.currentThread().getName().equals(Mainclass.getT3name())) {

						RunClass.robot_thread3 = false;
					}


				} else {

					if (RunClass.currentThread().getName().equals(Mainclass.getT1name())) {

						RunClass.robot_thread1 = true;

					} else if (RunClass.currentThread().getName().equals(Mainclass.getT2name())) {

						RunClass.robot_thread2 = true;

					} else if (RunClass.currentThread().getName().equals(Mainclass.getT3name())) {

						RunClass.robot_thread3 = true;
					}

				}
			}
		}
	}
}