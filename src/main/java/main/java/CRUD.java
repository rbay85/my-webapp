
package main.java;


import java.util.Date;


public class CRUD {

    public static void main(String[] args) {



        Client client5 = new Client(
                //"Ivan",
                //"Boiko",
                //new Date( 85,5,25 ),
                //"0904 080905",
                //"Moscow ....",
                //"ivan_boiko@mail.ru",
                //"qwerty"
        );

        // проверка геттера
        System.out.println( "\n " + client5.getAddress() );
        System.out.println( "\n " + client5.getBirthDay() );

        // проверка сеттера: клиент переехал из Москвы в Домбай
        client5.setAddress("KCHR, Dombay ....");

        // все одной строкой
        System.out.println( client5.toString() );

        Tariff tariff2 = new Tariff(
                //"Optimum",
                //350.00
        );
        System.out.println( tariff2.toString() );

        Contract contract2 = new Contract(
                //"+7(909)450-30-50",
                //2,
                //1,
                //0
        );
        System.out.println( contract2.toString() );

        Option option3 = new Option(
                //"10Gb internet",
                //50.00,
                //100.00
        );
        System.out.println( option3.toString() );
    }
}
