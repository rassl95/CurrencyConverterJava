package com.rassl.github.service;

import com.rassl.github.model.Currency;
import org.w3c.dom.Document;

import java.util.List;

/**
 *
 */
public interface CurrencyService<T,K> {
    /**
     *
     * @param fileFormat
     * @return
     */
    List<Currency<T, K>> createCollectionFromFile(String fileFormat);
    /**
     *
     * @return
     */
    Document downloadXml();
    /**
     *
     */
    void savingXmlFromConfig();
}
