import java.net.*;
import javax.swing.text.html.parser.*;
import javax.swing.text.html.*;
import javax.swing.text.*;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.TreeSet;

public class RetrievePage extends HTMLEditorKit.ParserCallback {

	public static ArrayList<String> links = new ArrayList<String>();

public static void main(String[] args) throws Exception{




	URL url = new URL("https://stackoverflow.com");

	Reader reader = new InputStreamReader((InputStream) url.getContent());

	new ParserDelegator().parse(reader, new Page(), true);

	for(int i = 0; i < links.size(); i++) {
		System.out.println(links.get(i));
	}

}

public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {

       if (t == HTML.Tag.A) {
            String link = null;

            Enumeration <?> attributeNames = a.getAttributeNames();

            if (attributeNames.nextElement().equals(HTML.Attribute.HREF))
                link = a.getAttribute(HTML.Attribute.HREF).toString();

                if(link != null) {
            	    links.add(link);
			    }
            }
       }



   }

