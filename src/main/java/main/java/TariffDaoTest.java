package main.java;

import org.junit.Test;
import java.util.List;


public class TariffDaoTest {

    TariffDao tariffDao= new TariffDao();

    @Test
    public void testAdd() throws Exception {

        //Создаем контракт для записи в БД
        Tariff tariff1 = new Tariff();
        tariff1.setName( "Maximum" );
        tariff1.setPrice( 250 );
        //tariff1.setOptionList( );

        //Записали в БД
        tariffDao.add( tariff1 );
    }

    @Test
    public void testGetAll() throws Exception {

        //Получаем всех клиентов с БД
        List<Tariff> tariffs = tariffDao.getAll();

        //Выводим полученый список клиентов
        for( Tariff c : tariffs ){
            System.out.println( c );
        }
    }

    @Test
    public void testDelete() throws Exception {

        //Удалем первую запись в БД
        tariffDao.delete( 1 );
    }
}