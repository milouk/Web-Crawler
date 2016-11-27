import java.util.ArrayList;
import java.net.*;

public class TestDatabase {
	public static void main(String args[]) throws MalformedURLException {
		ArrayList<URL> arrayURLs = new ArrayList<URL>();

		URL url1 = new URL("http://www.aueb.gr/");
		URL url2 = new URL("http://www.aueb.gr/pages/about/index.php");
		URL url3 = new URL("http://www.aueb.gr/pages/metaptyxiaka/full-time.php");
		URL url4 = new URL("http://www.aueb.gr/pages/erevna/elkeopa.php");
		URL url5 = new URL("http://www.aueb.gr/pages/diaviou/index.php");

		arrayURLs.add(url1);
		arrayURLs.add(url2);
		arrayURLs.add(url3);
		arrayURLs.add(url4);
		arrayURLs.add(url5);

		String path = "C:\\Users\\Michalis\\Desktop\\test";
		System.out.println(path);
		for (int i = 0; i < arrayURLs.size(); i++) {
			System.out.println(arrayURLs.get(i));
		}

	}
}
