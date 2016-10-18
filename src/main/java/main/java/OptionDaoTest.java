package main.java;

import org.junit.Test;
import java.util.List;


public class OptionDaoTest {

    OptionDao optionDao= new OptionDao();

    @Test
    public void testAdd() throws Exception {

        //Создаем контракт для записи в БД
        Option option1 = new Option();
        option1.setName("2 GB internet");
        option1.setPrice( 100 );
        option1.setOnCost( 30 );
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
        optionDao.delete( 1 );
    }
}
