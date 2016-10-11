package main.java;

import org.junit.Test;

import java.util.Date;
import java.util.List;

public class ClientServiceTest {

    ClientService service = new ClientService();

    @Test
    public void testAdd() throws Exception {

        //Создаем клиента для записи в БД
        Client client1 = new Client(
                "Ivan",
                "Boiko",
                new Date( 85, 5, 25 ),
                "0904 080905",
                "Moscow ....",
                "ivan_boiko@mail.ru",
                "qwerty");

        //Записали в БД
        service.add( client1 );
    }

    @Test
    public void testGet() throws Exception {

        // создаем клиента для записи в БД
        Client client2 = new Client(
                "Petr",
                "Semko",
                new Date( 87, 11, 15 ),
                "0987 245907",
                "Spb.....",
                "petr_semko@mail.ru",
                "asdasd");

        //Записываем в БД
        service.add( client2 );

        //получние Петра с БД
        Client clientFromDB = service.get( client2.getId() );
        System.out.println( clientFromDB );
    }

    @Test
    public void testUpdate() throws Exception {
        //Создаем клиента для записи в БД
        Client client3 = new Client(
                "Vern",
                "Jambo",
                new Date( 73, 1, 13 ),
                "1745 473498",
                "Kiev.....",
                "vern_jambo@mail.ru",
                "123123");

        //Записываем в БД
        service.add( client3 );

        // Верн поменял паспорт
        client3.setPassNo( "0000 112233" );

        //Обновляем
        service.update( client3 );

        //Получаем обновленую запись
        Client updatedClient = service.get( client3.getId() );
        System.out.println( updatedClient );
    }

    @Test
    public void testGetAll() throws Exception {

        //Получаем всех клиентов с БД
        List<Client> clients = service.getAll();

        //Выводим полученый список клиентов
        for( Client c : clients ){
            System.out.println( c );
        }
    }

    @Test
    public void testDelete() throws Exception {

        //Удалем первую запись в БД
        service.delete( 1 );
    }
}
