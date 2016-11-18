
/* Class For getting HTTP responses from server */

import java.io.IOException;
import java.net.*;

public class ServerResponse {

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