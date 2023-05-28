package com.rassl.github;

import com.rassl.github.service.CurrencyService;
import com.rassl.github.service.impl.CurrencyServiceImpl;


public class Main {

    public static void main(String[] args) {

        CurrencyService<String, String> service = new CurrencyServiceImpl();
        service.saveXmlDataFromConfig();
        service.downloadXml();
        service.createCollectionFromFile();
        service.showListOfCurrencies();
        service.getValueOfCurrency();
    }
}










