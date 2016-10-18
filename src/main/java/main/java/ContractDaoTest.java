package main.java;

import org.junit.Test;
import java.util.List;


public class ContractDaoTest {

    ContractDao contractDao= new ContractDao();

    @Test
    public void testAdd() throws Exception {

        //Создаем контракт для записи в БД
        Contract contract1 = new Contract();
        contract1.setPhone( "(901)456-4275" );
        //contract1.setTariff(  );
        //contract1.setOptionList(  );
        contract1.setIs_locked( 0 );

        //Записали в БД
        contractDao.add( contract1 );
    }

    @Test
    public void testGetAll() throws Exception {

        //Получаем всех клиентов с БД
        List<Contract> contracts = contractDao.getAll();

        //Выводим полученый список клиентов
        for( Contract c : contracts ){
            System.out.println( c );
        }
    }

    @Test
    public void testDelete() throws Exception {

        //Удалем первую запись в БД
        contractDao.delete( 1 );
    }
}