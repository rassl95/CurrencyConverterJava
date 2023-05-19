package com.rassl.github.Service;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


public class DowloadXml {

    private DowloadXml() {
    }

    public static Document dowloadXml() {
        try {
            URL url = new URL(new SavingXmlFromConfig().getUrl() + ServiceCurrentDate.getDate());
            Path file = Path.of(new SavingXmlFromConfig().getFilePath());

            try (InputStream inputStream = url.openStream()) {
                Files.copy(inputStream, file, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.out.println("URL's problem");
            }
            try {
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(new SavingXmlFromConfig().getFilePath());
                return document;
            } catch (ParserConfigurationException e) {
                System.out.println("XML parser configuration issues");
            } catch (SAXException e) {
                System.out.println("Problem encountered while processing XML");
            }
        } catch (MalformedURLException e) {
            System.out.println("URL address problem");
        } catch (IOException e) {
            System.out.println("IO problem");
        }
        return null;
    }
}