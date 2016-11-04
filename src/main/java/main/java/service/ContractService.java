package main.java.service;

import main.java.dao.ContractDao;
import main.java.entity.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ContractService {

    @Autowired
    private ContractDao contractDao;

    // возврещаем контрак по номеру телефона
    @Transactional
    public Contract getByPhone ( String phone ){

        Contract contract = contractDao.getByPhone( phone );
        return contract;
    }

    // администратор блокирует и разблокирует
    @Transactional
    public String adminLock (String phone, String condition) {

        Contract contract = contractDao.getByPhone( phone );
        String message;

        if ( condition.equals( "lock" )){
            contract.setIs_locked( 2 );
            contractDao.update( contract );
            message = " Contract was successfully locked";
        } else if ( condition.equals( "unlock" ) ) {
            contract.setIs_locked( 0 );
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
            if ( contract.getIs_locked() != 2 ) {
                contract.setIs_locked( 1 );
                contractDao.update( contract );
                message = " Your contract was successfully locked";
            } else {
                message = " Your contract is already locked by our operator";
            }

        } else if ( condition.equals( "unlock" ) ) {
            if ( contract.getIs_locked() != 2 ) {
                contract.setIs_locked( 0 );
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

    // перенести сюда действия из ContractDaoTest !
}
