package main.java.test;

import main.java.dao.ClientDao;
import main.java.dao.ContractDao;
import main.java.dao.OptionDao;
import main.java.dao.TariffDao;
import main.java.entity.Contract;
import main.java.entity.Option;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class ContractDaoTest {

    ContractDao contractDao = new ContractDao();

    @Test
    public void testUpdate() throws Exception {

        //Создаем контракт для записи в БД
        Contract contract1 = new Contract();
        // добавляем номер телефона
        contract1.setPhone( "(910)005-0022" );  // ловить MySQLIntegrityConstraintViolationException - неуникальный номер, ConstraintViolationException - неверный номер
        // добавляем клиента
        ClientDao clientDao = new ClientDao();
        contract1.setClient( clientDao.get( 10 ));
        //добавляем тариф
        TariffDao tariffDao = new TariffDao();
        contract1.setTariff( tariffDao.get( 2 ));
        // добавляем опции
        OptionDao optionDao = new OptionDao();
        Option option1 = optionDao.get( 1 );
        Option option2 = optionDao.get( 4 );
        Option option3 = optionDao.get( 7 );
        Option option4 = optionDao.get( 10 );
        Option option5 = optionDao.get( 11 );
        // в список
        List<Option> optionList = Arrays.asList(
                option1,
                option2,
                option3,
                option4,
                option5 );
        // список в контракт
        contract1.setOptionList( optionList );
        contract1.setIs_locked( 0 );

        //Записали в БД
        contractDao.update( contract1 );
    }

    @Test
    public void testUpdate1() throws Exception {

        // достаем контракт из БД
        Contract contract = contractDao.get( 12 ); // ловить NullPointerException
        // меняем номер телефона
        contract.setPhone( "(902)090-1177" );

        //Записали в БД
        contractDao.update( contract );
    }

    @Test
    public void testGetByPhone() throws Exception {

        //Получаем все контракты с БД
        Contract contract = contractDao.getByPhone( "(906)hgk" ); // ловить NoResultException

        //Выводим полученый контракт
        System.out.println( contract.getClient() );
    }

    @Test
    public void testGetAll() throws Exception {

        // Получаем все контракты с БД
        List<Contract> contracts = contractDao.getAll();

        // Выводим полученый список контрактов
        for( Contract c : contracts ){
            System.out.println( c );
        }
    }

    @Test
    public void testDelete() throws Exception {

        //Удалем первую запись в БД
        contractDao.delete( 1 );     // ловить IllegalArgumentException
    }
}