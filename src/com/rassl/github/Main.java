package com.rassl.github;

import com.rassl.github.Model.Currency;
import com.rassl.github.Service.CreationCollection;
import com.rassl.github.Service.ServiceCurrentDate;

import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Currency<String, String>> list = new CreationCollection().parseXmlFile();

        for (Currency<String, String> currency : list) {
            System.out.print(currency.getValuteName() + " ");
        }
        System.out.println("\n" + "Enter some currency symbol: ");

// Не понимаю почему когда переменной x присваиваем scanner.next() то все работает,
//  а когда напрямую передаем в метод equalsIgnoreCase(scanner.next())   то не работает!?
        boolean flag = false;
        String x= scanner.next();
        for (Currency<String, String> currency : list) {
            if (currency.getValuteName().equalsIgnoreCase(x)) {
                System.out.println(currency.getValuteValue() + " " + ServiceCurrentDate.getDate());
                flag = true;
                break;
            }
        }
        if (!flag) {
            System.out.println("Wrong symbol");
        }
        scanner.close();
    }
}








