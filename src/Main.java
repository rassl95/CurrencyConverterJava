import org.w3c.dom.Document;

import java.util.Map;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        ConvertingToXml converter = new ConvertingToXml();
        Document document = converter.getDocument();

        Map<String, Double> collection = new Currency<>(document);
        for (Map.Entry<String, Double> entry : collection.entrySet()) {
            System.out.print(entry.getKey() + " ");
        }
        System.out.println("\n" + "Enter some currency symbol:");

        // ? не получилось реализовать цикл поиска без учета регистра
        for (String key : collection.keySet()) { // ? не используется переменная, есть ли другая реализация
            String x = scanner.next();
            if (collection.containsKey(x)) {
                System.out.println(collection.get(x) + "   " + GettingCurrentDate.getCurrentDate());
            } else System.out.println("Wrong symbol!");
        }
        scanner.close();
    }
}







