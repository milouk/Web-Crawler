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