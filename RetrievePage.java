import java.net.URL;
import javax.swing.text.html.parser.*;
import javax.swing.text.html.*;
import javax.swing.text.*;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.TreeSet;



public class RetrievePage extends HTMLEditorKit.ParserCallback {

    static int j = 0;
	static int i = 1;
	static int k = 0;
	public static ArrayList<String> links = new ArrayList<String>();
	public static final String link = "https://stackoverflow.com";




public static void main(String[] args) throws Exception{

    for (int j = 0; j < 4; j++) {
    System.out.println("\n\n\n\n\n" + j);

    	if(k == 0) {

    		start(link);

		    	System.out.println(links.size() + "\n\n\n\n\n");

	    } else {
             System.out.println(links.get(1));
			start(links.get(1));
					    	System.out.println(links.size() + "\n\n\n\n\n");

			i++;
		}

    }

	for(int i = 0; i < links.size(); i++) {
			System.out.println(links.get(i));
	}
	System.out.println("\n\n\n" + links.size());
}




public static void start(String link) throws Exception {

	k++;

	URL url = new URL(link);

	Reader reader = new InputStreamReader((InputStream) url.getContent());

	new ParserDelegator().parse(reader, new Page(), true);

}




public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {


       String link = null;

       if (t == HTML.Tag.A) {


           Enumeration <?> attributeNames = a.getAttributeNames();

            if (attributeNames.nextElement().equals(HTML.Attribute.HREF))
                link = a.getAttribute(HTML.Attribute.HREF).toString();

                if(link != null) {
                      if(link.contains("http")) {
            	          links.add(link);
				      } else {
						  links.add(Page.link + link);
					  }
			    }
            }

       }

   }