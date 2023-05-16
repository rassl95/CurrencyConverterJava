import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;

public class Currency<T, K> {
    private T t;
    private K k;
    private Document document;

    public Currency(Document document) {
        this.k = k;
        this.t = t;
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }

    public T getT() {
        return t;
    }

    public K getK() {
        return k;
    }

    void setT(T t) {
        this.t = t;
    }

    void setK(K k) {
        this.k = k;
    }

    public Map<T, K> createCollection() {
        Map<T, K> dataMap = new HashMap<>();
        NodeList valuteList = getDocument().getElementsByTagName("Valute");
        if (valuteList != null) {
            for (int i = 0; i < valuteList.getLength(); i++) {
                Element valuteElement = (Element) valuteList.item(i);

                // Извлекаем нужные данные из элемента "Valute"
                T valuteName = (T) valuteElement.getElementsByTagName("CharCode").item(0).getTextContent();
                K valuteValue = (K) valuteElement.getElementsByTagName("Value").item(0).getTextContent();

                dataMap.put(valuteName, valuteValue);
            }
        }
        return dataMap;
    }
}
