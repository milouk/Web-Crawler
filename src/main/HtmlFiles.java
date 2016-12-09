import java.io.*;
import java.util.ArrayList;
import java.net.*;

public class HtmlFiles {

	public static ArrayList<String> lines = new ArrayList<String>();

	public static void createFile(String link, String path, int index) throws IOException {

		URL url = new URL(link);
		URLConnection connection = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null) {

			lines.add(inputLine);
		}
		in.close();
	}
}


