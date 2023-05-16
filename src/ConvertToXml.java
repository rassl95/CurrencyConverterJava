import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface ConvertToXml  {
    Document convertToXml() throws IOException, ParserConfigurationException, SAXException;
}
