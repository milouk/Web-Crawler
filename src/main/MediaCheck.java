package com.complet;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * <h1>Check for Media - MediaCheck.java</h1> The MediaCheck Class is a class
 * that checks whether or not a given URL is an Image.
 * <p>
 * <b>Note: </b>Only checks for Images and not Videos
 * <p>
 *
 * @author Complet
 * @version 6.0
 * @since 2016-12-30
 */

public class MediaCheck {

	/**
	 * <p>
	 * This is the only method of the class.<br>
	 * The only parameter is the URL.
	 * <p>
	 * The URL is parsed into an image and if the result is not null, that means
	 * that the URL is as an image and our LinkRetrieve class acts accordingly.
	 * <p>
	 *
	 * @param url
	 *            The URL that is retrieved from the LinkRetrieve Class.
	 * @return <code>true</code> or <code>false</code> based on the URL (Not
	 *         Image/ Image).
	 * @exception IOException
	 *                Input/Output exception
	 */

	public static boolean media(URL url) {

		Image image = null;
		try {
			image = ImageIO.read(url);
		} catch (IOException e) {
			System.err.println(e + "" + url);
		}
		if (image != null) {
			return false;
		} else {
			return true;
		}
	}
}
