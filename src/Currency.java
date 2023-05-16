import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Currency<T, K> implements Map<T, K> {
    private final Document document;
    Map<T, K> dataMap = new HashMap<>();

    public Currency(Document document) {
        this.document = document;
        createCollection();
    }

     Document getDocument() {
        return document;
    }


    public Map<T, K> createCollection() {
        NodeList valuteList = getDocument().getElementsByTagName("Valute");
        if (valuteList != null) {
            for (int i = 0; i < valuteList.getLength(); i++) {
                Element valuteElement = (Element) valuteList.item(i);

                // Извлекаем нужные данные из элемента "Valute"
 //  ? Idea пишет "Unchecked cast: 'java.lang.String' to 'T' "; что ей не нравится и как можно по другому реализовать
                T valuteName = (T) valuteElement.getElementsByTagName("CharCode").item(0).getTextContent();
                K valuteValue = (K) valuteElement.getElementsByTagName("Value").item(0).getTextContent();

                dataMap.put(valuteName, valuteValue);
            }
        }
        return dataMap;
    }

    @Override
    public int size() {
        return dataMap.size();
    }

    @Override
    public boolean isEmpty() {
        return dataMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return dataMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return dataMap.containsValue(value);
    }

    @Override
    public K get(Object key) {
        return dataMap.get(key);
    }

    @Override
    public K put(T key, K value) {
        return dataMap.put(key, value);
    }

    @Override
    public K remove(Object key) {
        return dataMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends T, ? extends K> m) {
        dataMap.putAll(m);
    }

    @Override
    public void clear() {
        dataMap.clear();
    }

    @Override
    public Set<T> keySet() {
        return dataMap.keySet();
    }

    @Override
    public Collection<K> values() {
        return dataMap.values();
    }

    @Override
    public Set<Entry<T, K>> entrySet() {
        return dataMap.entrySet();
    }
}
