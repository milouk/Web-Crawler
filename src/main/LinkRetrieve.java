package com.complet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

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
						|| link.endsWith(".mp") || link.endsWith(".pdf") || link.contains("://dl.")
						|| link == "javascript:void(0)") || link.endsWith(".png") || link.endsWith(".gz")
						|| link.endsWith(".gif") || link.endsWith(".css") || link.endsWith(".gif")) {

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
