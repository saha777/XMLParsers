package domparser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomStarter {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {

        DomWriter dw = new DomWriter(new File("bussines.xml"));
        dw.createXmlFile();

        DomReader dr = new DomReader(new File("bussines.xml"));

        for (Country country : dr.getCountries())
            System.out.println(country.toString());

    }
}