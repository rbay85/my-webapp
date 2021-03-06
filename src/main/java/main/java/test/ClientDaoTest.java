package main.java.test;

import main.java.dao.ClientDao;
import main.java.entity.Client;
import org.junit.Test;

import java.util.Date;
import java.util.List;


public class ClientDaoTest {

    ClientDao clientDao = new ClientDao();

    @Test
    public void testAdd() throws Exception {

        //Создаем клиента для записи в БД
        Client client1 = new Client();
        client1.setFirstName( "Ivan" );
        client1.setLastName( "Boiko" );
        client1.setBirthDay( new Date( 85,5,25 ));
        client1.setPassNo( "0904 080905" );
        client1.setAddress( "Moscow ...." );

        //Записали в БД
        clientDao.add( client1 );
    }

    @Test
    public void testGet() throws Exception {

        // создаем клиента для записи в БД
        Client client2 = new Client();
        client2.setFirstName( "Anna" );
        client2.setLastName( "Smith" );
        client2.setBirthDay( new Date( 90, 9, 1 ));
        client2.setPassNo( "0967 357583" );
        client2.setAddress( "Lviv....." );

        //Записываем в БД
        clientDao.add( client2 );

        //получние Петра с БД
        Client clientFromDB = clientDao.get( client2.getId() );
        System.out.println( clientFromDB );
    }

    @Test
    public void testUpdate() throws Exception {

        //Создаем клиента для записи в БД
        Client client3 = new Client();
        client3.setFirstName( "Vern" );
        client3.setLastName( "Jambo" );
        client3.setBirthDay( new Date( 73, 1, 13 ));
        client3.setPassNo( "1745 473498" );
        client3.setAddress( "Kiev....." );

        //Записываем в БД
        clientDao.add( client3 );

        // Верн поменял паспорт
        client3.setPassNo( "0000 112233" );

        //Обновляем
        clientDao.update( client3 );

        //Получаем обновленую запись
        Client updatedClient = clientDao.get( client3.getId() );
        System.out.println( updatedClient );
    }

    @Test
    public void testGetAll() throws Exception {

        //Получаем всех клиентов с БД
        List<Client> clients = clientDao.getAll();

        //Выводим полученый список клиентов
        for( Client c : clients ){
            System.out.println( c );
        }
    }

    @Test
    public void testDelete() throws Exception {

        //Удалем запись в БД
        clientDao.delete( 12 );
    }
}
