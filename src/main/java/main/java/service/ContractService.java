package main.java.service;

import main.java.dao.ContractDao;
import main.java.entity.Client;
import main.java.entity.Contract;

import java.util.List;

public class ContractService {

    // возврещаем контрак по номеру телефона
    public Contract getByPhone ( String phone ){

        ContractDao contractDao = new ContractDao();
        Contract contract = contractDao.getByPhone( phone );
        return contract;
    }

    // администратор блокирует и разблокирует
    public String adminLock (String phone, String condition) {

        ContractDao contractDao = new ContractDao();
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
    public String clientLock ( int id, String condition ) {

        String message = "";

        ContractDao contractDao = new ContractDao();
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
}
