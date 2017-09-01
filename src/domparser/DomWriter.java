package domparser;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class DomWriter {
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    Document document;
    File file;

    public DomWriter(File file) {
        this.file = file;
        try{
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            document = builder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void createXmlFile(){
        Element countries = document.createElement("countries");
        document.appendChild(countries);

        countries.appendChild(createCountry(new Country(4233, "Bulgaria", "Europe")));
        countries.appendChild(createCountry(new Country(2743, "Germany", "Europe")));

        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        try{
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult stream = new StreamResult(file);
            transformer.transform(source, stream);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private Element createCountry(Country country){
        Element newCountry = document.createElement("country");

        Attr attrName = document.createAttribute("name");
        attrName.setNodeValue(country.getName());

        Element id = document.createElement("id");
        id.appendChild(document.createTextNode(String.valueOf(country.getId())));
        Element continent = document.createElement("continent");
        continent.appendChild(document.createTextNode(country.getContinent()));

        newCountry.setAttributeNode(attrName);
        newCountry.appendChild(id);
        newCountry.appendChild(continent);
        return newCountry;
    }
}
