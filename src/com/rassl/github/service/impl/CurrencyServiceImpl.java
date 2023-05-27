package com.rassl.github.service.impl;

import com.rassl.github.model.Currency;
import com.rassl.github.service.CurrencyService;
import org.w3c.dom.Document;

import java.util.List;

public class CurrencyServiceImpl implements CurrencyService<String, String> {
    @Override
    public List<Currency<String, String>> createCollectionFromFile(String fileFormat) {
        return null;
    }

    @Override
    public Document downloadXml() {
        return null;
    }

    @Override
    public void savingXmlFromConfig() {

    }
}
