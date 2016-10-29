package main.java.service;

import main.java.dao.ContractDao;
import main.java.entity.Contract;

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

        return message;
    }
}
