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
}
