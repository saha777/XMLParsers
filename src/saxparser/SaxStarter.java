package saxparser;

import org.xml.sax.SAXException;
import saxparser.Handler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class SaxStarter {
    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        Handler handler = new Handler();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(new File("../bussines.xml"), handler);
        }catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<Integer, Country> data = handler.getData();
        for (Map.Entry<Integer, Country> dMap: data.entrySet()){
            System.out.println(dMap.getValue());
        }
    }
}
