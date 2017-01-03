package com.complet;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <h1>Get Response from server - ServerResponse.java</h1> The ServerResponse
 * class was created to validate a given URL.
 * <p>
 * <b>Note: </b>The URL given must be an a valid form.
 *
 * @author Complet
 * @version 6.0
 * @since 2016-12-30
 */

public class ServerResponse {

	/**
	 * Being the only method of this class, ServerResponse.response method
	 * checks whether or not a URL is valid (NOT Valid Example: Error 404 Not
	 * Found)
	 * <p>
	 * Creates a connection with the server and gets a response message.<br>
	 * <li>If the message is "OK", that means that the URL is valid and our
	 * LinkRetrieve acts accordingly.
	 * <li>If the message is "Not Found", then obviously the URL content is not
	 * valid and our LinkRetrieve acts accordingly
	 * <p>
	 *
	 * @param url
	 *            The URL that is retrieved from the LinkRetrieve Class.
	 * @return response Is a string "OK" or "Not Found" based on the response
	 *         message from the server.
	 * @throws IOException
	 *             Input/Output Exception
	 */

	public static String response(URL url) throws IOException {

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		try {
			connection.connect();
			String response = connection.getResponseMessage();
			connection.disconnect();
			return response;
		} catch (Exception exp) {
			return exp.getMessage();
		}
	}
}
