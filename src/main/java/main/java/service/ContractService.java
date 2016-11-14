package main.java.service;

import main.java.dao.ClientDao;
import main.java.dao.ContractDao;
import main.java.dao.OptionDao;
import main.java.dao.TariffDao;
import main.java.entity.Contract;
import main.java.entity.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ContractService {

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private TariffDao tariffDao;

    @Autowired
    private OptionDao optionDao;

    // выводим все контракты
    @Transactional
    public List<Contract> getAllContracts() {

        List<Contract> contractList = contractDao.getAll();
        return contractList;
    }

    // добавляем контракт
    @Transactional
    public void add( String phone, String clientId, String tariffId ) {

        Contract contract = new Contract();
        contract.setPhone( phone );
        contract.setClient( clientDao.get( Integer.parseInt( clientId )));
        contract.setTariff( tariffDao.get( Integer.parseInt( tariffId )));

        contractDao.add( contract );
    }

    // добавляем опцию из тарифа в контракт
    @Transactional
    public String addOptionInContract( String optionId, String contractId ){

        String message = "";

        //Option option = optionDao.get( Integer.parseInt( optionId ));
        //Contract contract = contractDao.get( Integer.parseInt( contractId ));

        message = "it is OK" + optionId  + contractId;

        return message;
    }

    // удаляем контракт по Id
    @Transactional
    public String delete( String id ) {

        contractDao.delete( Integer.parseInt( id ) );
        return "contract successfully deleted";
    }

    // возврещаем контракт по номеру телефона
    @Transactional
    public Contract getByPhone ( String phone ){

        Contract contract = contractDao.getByPhone( phone );
        return contract;
    }

    // администратор блокирует и разблокирует
    @Transactional
    public String adminLock (String id, String condition) {

        Contract contract = contractDao.get( Integer.parseInt( id ));
        String message;

        if ( condition.equals( "lock" )){
            contract.setIsLocked( 2 );
            contractDao.update( contract );
            message = " Contract was successfully locked";
        } else if ( condition.equals( "unlock" ) ) {
            contract.setIsLocked( 0 );
            contractDao.update( contract );
            message = " Contract was successfully unlocked";
        } else {
            message = " Choose an action, please! ";
        }
        return message;
    }

    // клиент блокирует и разблокирует
    @Transactional
    public String clientLock ( int id, String condition ) {

        String message;

        Contract contract = contractDao.get( id );

        if ( condition.equals( "lock" )){
            if ( contract.getIsLocked() != 2 ) {
                contract.setIsLocked( 1 );
                contractDao.update( contract );
                message = " Your contract was successfully locked";
            } else {
                message = " Your contract is already locked by our operator";
            }

        } else if ( condition.equals( "unlock" ) ) {
            if ( contract.getIsLocked() != 2 ) {
                contract.setIsLocked( 0 );
                contractDao.update( contract );
                message = " Your contract was successfully unlocked";
            } else {
                message = " Sorry, but your contract is locked by our operator";
            }

        } else {
            message = " Choose an action, please! ";
        }
        return message;
    }
}
