
package main.java;

import java.util.Calendar;
import java.util.Date;


public class CRUD {

    public static void main(String[] args) {

        Client client5 = new Client(
                5,
                "Ivan",
                "Boiko",
                new Date(1985,6,25),
                1034080905,
                "Moscow ....",
                "+7(909)450-30-50",
                "ivan_boiko@mail.ru",
                "qwerty");
        // проверка геттера
        System.out.println( "\n " + client5.getAddress() );
        // проверка сеттера: клиент переехал из Москвы в Домбай
        client5.setAddress("KCHR, Dombay ....");
        // все одной строкой
        System.out.println( client5.toString() );

        Tariff tariff2 = new Tariff(
                2,
                "Optimum",
                350.00,
                "Big Internet");
        System.out.println( tariff2.toString() );

        Contract contract3 = new Contract(
                3,
                "+7(909)450-30-50",
                "Optimum",
                "Big Internet");
        System.out.println( contract3.toString() );

        Option option1 = new Option(
                1,
                "Big Internet",
                50.00,
                100.00);
        System.out.println( option1.toString() );
    }
}
