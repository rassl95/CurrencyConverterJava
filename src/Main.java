import org.w3c.dom.Document;

import java.util.Map;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Document document =  new ConvertingToXml().convertToXml();
        Currency<String, String> currency = new Currency<>(document);
        Map<String, String> collection = currency.createCollection();
        for (Map.Entry<String, String> entry : collection.entrySet()) {
            System.out.print(entry.getKey() + " ");
        }

        System.out.println("\n" + "Enter some currency symbol:");
        for (String key : collection.keySet()) {
            String x = scanner.next();
            if (collection.containsKey(x)) {
                System.out.println(collection.get(x) + "   " + GettingCurrentDate.getCurrentDate());
            } else System.out.println("Wrong symbol!");
        }
        scanner.close();
    }
}







