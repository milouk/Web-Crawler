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
 * <h1>Create Files - HtmlFiles.java</h1> The HtmlFiles Class is responsible for
 * the file system related to our Web Crawler.
 * <p>
 * <b>Note: </b>Complet team suggests careful use of this class.
 * <p>
 *
 * @author Complet
 * @version 6.0
 * @since 2017-01-02
 */

public class HtmlFiles {

	private static ArrayList<String> lines = new ArrayList<String>();
	private static Path filepath;

	/**
	 * <p>
	 * CheckPath is the first method of this class. Gets a path(String) as a
	 * parameter and firstly checks if the path exists.If it exists, it creates
	 * a new folder with the name "HTML_Files" and concatenates the path with
	 * Mainclass' path variables. If the path is not valid, then the user must
	 * enter again the path and checkpath method is called recursively.
	 * <p>
	 *
	 * @param path
	 *            The path that we want the HTML_Files directory to be created.
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
	 * CreateFile is the second method of this class. This method creates the
	 * files.
	 * <p>
	 * File names have a specific structure. Name: index + ".html", example
	 * "0.html". This may seem inconvenient, but when combined with the Sql
	 * server URL list the search engine will be able to locate and retrieve the
	 * HTML code really easily.
	 * <p>
	 *
	 * @param link
	 *            This link's html code will be saved to the file created.
	 * @param path
	 *            This is the path that we want each file to be created (This
	 *            path was checked with checkpath method).
	 * @param index
	 *            Index is an integer variable. Files created will be assigned
	 *            with the URL list on the Sql server.
	 * @throws IOException
	 *             Input/Output Exception while creating the files
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
	 * DeleteDirectory is the last method of this class. Checks if the file
	 * given exists. If so, recursively deletes every file in this directory.
	 *
	 * @param directory
	 * @return {@link com.complet.HtmlFiles#deleteDirectory(File)} After
	 *         deleting every file, the program returns the deletion of the
	 *         Directory.
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
