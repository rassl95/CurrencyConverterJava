package com.rassl.github.service.impl;

import com.rassl.github.model.Currency;
import com.rassl.github.service.CurrencyService;
import com.rassl.github.service.ServiceCurrentDate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class CurrencyServiceImpl implements CurrencyService<String, String> {
    private String url;
    private String filePath;
    private List<Currency<String, String>> list = new ArrayList<>();
    private Document document;


    @Override
    public void saveXmlDataFromConfig() {

        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("config/config.properties")) {
            properties.load(input);
            url = properties.getProperty("url");
            filePath = properties.getProperty("xml.file.path");
        } catch (IOException e) {
            System.out.println("Config file's problem");
        }
    }


    @Override
    public Document downloadXml() {

        try {
            URL url1 = new URL(url + ServiceCurrentDate.getDate());
            Path file1 = Path.of(filePath);

            try (InputStream inputStream = url1.openStream()) {
                Files.copy(inputStream, file1, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.out.println("URL's problem");
            }
            try {
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                document = documentBuilder.parse(filePath);
            } catch (ParserConfigurationException e) {
                System.out.println("XML parser configuration issues");
            } catch (SAXException e) {
                System.out.println("Problem encountered while processing XML");
            }
            return document;
        } catch (MalformedURLException e) {
            System.out.println("URL address problem");
        } catch (IOException e) {
            System.out.println("IO problem");
        }
        return document;
    }


    @Override
    public List<Currency<String, String>> createCollectionFromFile() {

        NodeList valuteList = Objects.requireNonNull(document.getElementsByTagName("Valute"));

        for (int i = 0; i < valuteList.getLength(); i++) {
            Element valuteElement = (Element) valuteList.item(i);

            String valuteName = parseValue(valuteElement, "CharCode");
            String valuteValue = parseValue(valuteElement, "Value");
            Currency<String, String> currency = new Currency<>(valuteName, valuteValue);
            currency.setValuteName(valuteName);
            currency.setValuteValue(valuteValue);
            list.add(currency);
        }
        return list;
    }

    @Override
    public void showListOfCurrencies() {
        for (Currency<String, String> currency : list) {
            System.out.print(currency.getValuteName() + " ");
        }
        System.out.println("\n" + "Enter some currency symbol: ");
    }

    @Override
    public void getValueOfCurrency() {
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;
        String x = scanner.next();
        for (Currency<String, String> currency : list) {
            if (currency.getValuteName().equalsIgnoreCase(x)) {
                System.out.println(currency.getValuteValue() + " " + ServiceCurrentDate.getDate());
                flag = true;
                break;
            }
        }
        if (!flag) {
            System.out.println("Wrong symbol");
        }
        scanner.close();
    }

    private static <T> T parseValue(Element element, String tagName) {
        String value = element.getElementsByTagName(tagName).item(0).getTextContent();
        return (T) value;
    }
}



