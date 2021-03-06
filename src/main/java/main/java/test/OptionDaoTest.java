package main.java.test;

import main.java.dao.OptionDao;
import main.java.entity.Option;
import org.junit.Test;
import java.util.List;


public class OptionDaoTest {

    OptionDao optionDao = new OptionDao();

    @Test
    public void testAdd() throws Exception {

        //Создаем контракт для записи в БД
        Option option1 = new Option();
        option1.setName( "call service" );
        option1.setPrice( 0 );
        option1.setOnCost( 0 );
        //option1.setNecessaryOptionList(  );
        //option1.setIncompatibleOptionList(  );

        //Записали в БД
        optionDao.add( option1 );
    }

    @Test
    public void testGetAll() throws Exception {

        //Получаем всех клиентов с БД
        List<Option> options = optionDao.getAll();

        //Выводим полученый список клиентов
        for( Option c : options ){
            System.out.println( c );
        }
    }

    @Test
    public void testDelete() throws Exception {

        //Удалем первую запись в БД
        optionDao.delete( 12 );
    }
}
