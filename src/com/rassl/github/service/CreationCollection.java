package com.rassl.github.service;

import com.rassl.github.model.Currency;

import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CreationCollection<T, K> {


    public List<Currency<T, K>> parseXmlFile() {
        List<Currency<T, K>> list = new ArrayList<>();

        NodeList valuteList = Objects.requireNonNull(DowloadXml.dowloadXml()).getElementsByTagName("Valute");

        for (int i = 0; i < valuteList.getLength(); i++) {
            Element valuteElement = (Element) valuteList.item(i);

            T valuteName = parseValue(valuteElement, "CharCode");
            K valuteValue = parseValue(valuteElement, "Value");
            Currency<T, K> currency = new Currency<>(valuteName, valuteValue);

            currency.setValuteName(valuteName);
            currency.setValuteValue(valuteValue);
            list.add(currency);
        }
        return list;
    }

    private static <T> T parseValue(Element element, String tagName) {
        String value = element.getElementsByTagName(tagName).item(0).getTextContent();
        return (T) value;
    }
}
