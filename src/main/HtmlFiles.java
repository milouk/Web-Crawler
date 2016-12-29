import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class HtmlFiles {

	public static ArrayList<String> lines = new ArrayList<String>();
	private static Path filepath;

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



	public static void createFile(String link, String path, int index) throws IOException {

		URL url = new URL(link);
		URLConnection connection = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null) {

			lines.add(inputLine);
		}
		in.close();

		Path filepath = Paths.get(path);

			if (Files.exists(filepath)) {
				path = ((path.concat("\\")).concat(Integer.toString(index)).concat(".html"));
				filepath = Paths.get(path);
				Files.write(filepath, lines, Charset.forName("UTF-8"));
			} else {

				do {
					System.out.printf("%s", "Enter valid path : ");
					path = Mainclass.inputpath.nextLine();
					filepath = Paths.get(path);
				} while (!Files.exists(filepath));

				Mainclass.path = path;
				path = ((path.concat("\\")).concat(Integer.toString(index)).concat(".html"));
				filepath = Paths.get(path);
				Files.write(filepath, lines, Charset.forName("UTF-8"));
			}

			lines.removeAll(lines);
	}

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


