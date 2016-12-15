import java.io.IOException;
import java.net.*;

public class ServerResponse {

	public static void response(URL url) throws IOException {

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	}

}