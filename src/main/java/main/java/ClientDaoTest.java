package main.java;

import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class ClientDaoTest {

    ClientDao clientDao = new ClientDao();

    @Test
    public void testAdd() throws Exception {

        //Создаем клиента для записи в БД
        Client client1 = new Client();
        Contract contract1 = new Contract();
        List<Contract> contractList = Arrays.asList( contract1 );

        contract1.setPhone( "(901)456-4275" );
        contract1.setIs_locked( 0 );

        client1.setFirstName( "Dima" );
        client1.setLastName( "Kotov" );
        client1.setBirthDay( new Date( 89, 1, 13 ));
        client1.setPassNo( "0405 215487" );
        client1.setAddress( "Lugi..." );
        client1.setContractList( contractList );
        client1.setEmail( "d.kotov@ya.ru" );
        client1.setPassWord( "qazqaz" );

        //Записали в БД
        clientDao.add( client1 );
    }

    @Test
    public void testGet() throws Exception {

        // создаем клиента для записи в БД
        Client client2 = new Client();
        client2.setFirstName( "Petr" );
        client2.setLastName( "Semko" );
        client2.setBirthDay( new Date( 87, 11, 15 ));
        client2.setPassNo( "0987 245907" );
        client2.setAddress( "Spb....." );
        client2.setEmail( "petr_semko@mail.ru" );
        client2.setPassWord( "asdasd" );

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
        client3.setEmail( "vern_jambo@mail.ru" );
        client3.setPassWord( "123123" );

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

        //Удалем первую запись в БД
        clientDao.delete( 1 );
    }
}
