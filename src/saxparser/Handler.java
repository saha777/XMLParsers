package saxparser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

public class Handler extends DefaultHandler{

    private Map<Integer, Country> data = new HashMap<>();
    private int id;
    private String name;
    private String element;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parsing ...");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End parsing ...");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        element = qName;
        if(element.equals("country"))
            name = attributes.getValue(0);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals("country")) {
            id = -1;
            name = "";
        }
        element = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(element.equals("id")){
            id = Integer.parseInt(new String(ch, start, length));
        }
        else if(element.equals("continent")){
            data.put(id, new Country(id, name, new String(ch, start, length)));
        }
    }

    public Map<Integer, Country> getData(){
        return data;
    }
}
