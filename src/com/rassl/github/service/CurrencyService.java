package com.rassl.github.service;

import com.rassl.github.model.Currency;
import org.w3c.dom.Document;

import java.util.List;

/**
 * This interface represents the main processing operations of the model
 */
public interface CurrencyService<T, K> {

    /**
     * This method saves XML data from  config file, indicates  from where and where to we transfer data
     */
    void saveXmlDataFromConfig();

    /**
     * This method loads XML data from the specified URL,
     * saves it to a file, and then parses the file to get a Document object
     */
    Document downloadXml();

    /**
     * This method reads a date from XML file, makes list of currency's objects
     */
    List<Currency<T, K>> createCollectionFromFile();

    /**
     * This method shows names of currencies
     */
    void showListOfCurrencies();

    /**
     * This method shows the exchange rate  of selected currency
     */
    void getValueOfCurrency();
}


