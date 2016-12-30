package com.complet;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * <h1>HtmlFiles.java</h1>
 * <p>
 * This class contains a static method that ensures the given path exists,
 * creates empty HTML Files in the given path and fills the empty files with each link's HTML code.<br>
 * The lines argument specifies an Arraylist that contains some {@link url}.
 * <p>
 *
 * @author Complet
 * @version 5.0
 *
 */
public class HtmlFiles {

	private static ArrayList<String> lines = new ArrayList<String>();
	private static Path filepath;

    /**
    * <p>
    * This method is used to check if path is valid and when the path is valid it creates a directory HTML_Files in this path
    * <p>
    *
    * @param path This is the given path
    * @return Nothing
    */
	public static void checkPath(String path) {

		if (Files.exists(Paths.get(path))) {
			File theDir = new File(path.concat("\\HTML_Files"));
			theDir.mkdir();
			Mainclass.setPath(path.concat("\\HTML_Files"));
			Mainclass.setPath2(path.concat("\\HTML_Files"));

		} else {

			System.out.printf("%s", "Enter valid path : ");
			path = Mainclass.getInputpath().nextLine();
			checkPath(path);
		}
	}

    /**
    * <p>
    * This method is make a connection with a URL ,store the HTML code into an Array List and the write it down to a file.
    *
    * <p>
    *
    * @param link This is the given link
    * @param path This is the given path
    * @param index The index of each link in finalist is its name with the extension html
    * @return Nothing
    * @exception IOException On input error.
    */
	public static void createFile(String link, String path, int index) throws IOException {

		URL url = new URL(link);

		URLConnection connection = url.openConnection();

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		String inputLine;

		while ((inputLine = in.readLine()) != null) {

			lines.add(inputLine);
		}

		in.close();

		path = ((path.concat("\\")).concat(Integer.toString(index)).concat(".html"));
		filepath = Paths.get(path);
		Files.write(filepath, lines, Charset.forName("UTF-8"));

		// reset list
		lines.removeAll(lines);
	}

    /**
    * <p>
    * This method recursively deletes a directory with or without files in it.
    *
    * <p>
    * @param directory Directory with the files
    * @return True if directory is succesfully deleted, False otherwise.
    */
	public static boolean deleteDirectory(File directory) {

		if (directory.exists()) {
			File[] files = directory.listFiles();
			if (null != files) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory()) {
						deleteDirectory(files[i]);
					} else {
						files[i].delete();
					}
				}
			}
		}
		return (directory.delete());
	}

}