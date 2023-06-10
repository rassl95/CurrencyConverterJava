package com.rassl.github.service.impl;

import com.rassl.github.model.Currency;
import com.rassl.github.service.CurrencyService;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;

public class CurrencyServiceImpl implements CurrencyService<String, String> {
    private String url;
    private String filePath;
    private List<Currency<String, String>> list = new ArrayList<>();
    private Document document;
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static LocalDate date = LocalDate.now();
    private static final Logger log = Logger.getLogger(CurrencyServiceImpl.class.getName());


    @Override
    public void saveXmlDataFromConfig() {

        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("config/config.properties")) {
            properties.load(input);
            url = properties.getProperty("url");
            filePath = properties.getProperty("xml.file.path");
        } catch (IOException e) {
            log.severe("Config file's problem "+e.getMessage());
        }
    }


    @Override
    public Document downloadXml() {

        try {
            URL url1 = new URL(url + getDate());
            Path file1 = Path.of(filePath);

            try (InputStream inputStream = url1.openStream()) {
                Files.copy(inputStream, file1, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                log.severe("URL's problem "+e.getMessage());
            }
            try {
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                document = documentBuilder.parse(filePath);
            } catch (ParserConfigurationException e) {
                log.severe("XML parser configuration issues "+ e.getMessage());
            } catch (SAXException e) {
                log.severe("Problem encountered while processing XML "+e.getMessage());
            }
            return document;
        } catch (MalformedURLException e) {
            log.severe("URL address problem "+e.getMessage());
        } catch (IOException e) {
            log.severe("IO problem "+e.getMessage());
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


        while (scanner.hasNext()) {
            boolean flag = false;
            String x = scanner.next();
            if (x.equalsIgnoreCase("exit")) break;
            for (Currency<String, String> currency : list) {
                if (currency.getValuteName().equalsIgnoreCase(x)) {
                    System.out.println(currency.getValuteValue() + " " + getDate());
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println("Wrong symbol");
            }
        }
        scanner.close();
    }


    private static <T> T parseValue(Element element, String tagName) {
        String value = element.getElementsByTagName(tagName).item(0).getTextContent();
        return (T) value;
    }

    public static String getDate() {
        return date.format(FORMAT);
    }

//      public static void setDate(String dateString) {
//        date = LocalDate.parse(dateString, FORMAT);
//    }
}


