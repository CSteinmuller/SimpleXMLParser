import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class SimpleParser {
    public SimpleParser() throws ParserConfigurationException
    {
    	DocumentBuilderFactory dbfact = DocumentBuilderFactory.newInstance();
    	builder = dbfact.newDocumentBuilder();
    	XPathFactory xpfact = XPathFactory.newInstance();
    	path = xpfact.newXPath();   	
    }
    
    public ArrayList<String> parse(String fname) throws SAXException, IOException, XPathExpressionException
    {
    	File f = new File(fname);
    	Document doc = builder.parse(f);
    	
    	ArrayList<String> items = new ArrayList<String>();
    	int itemCount = Integer.parseInt(path.evaluate("count(/items/item)", doc));
    	for (int j = 1; j <= itemCount; j++)
    	{
    		String description = path.evaluate("/items/item["+ j + "]/thing/description", doc);
    		String thing = description;
    		items.add(thing);
    	}
    	return items;
    }
    private DocumentBuilder builder;
    private XPath path;
}
