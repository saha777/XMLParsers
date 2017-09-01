package domparser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomReader {
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    Document document;
    public DomReader(File file) {
        try{
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            document = builder.parse(file);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<Country> getCountries(){
        NodeList nodeList = document.getElementsByTagName("country");
        List<Country> countryList = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++){
            Element el = (Element) nodeList.item(i);
            String name = el.getAttributes().getNamedItem("name").getNodeValue();
            int id = Integer.parseInt(el.getElementsByTagName("id").item(0).getChildNodes().item(0).getNodeValue());
            String continent = el.getElementsByTagName("continent").item(0).getChildNodes().item(0).getNodeValue();

            countryList.add(new Country(id, name, continent));
        }

        return countryList;
    }
}
