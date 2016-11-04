package main.java.service;

import main.java.dao.TariffDao;
import main.java.entity.Tariff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TariffService {

    @Autowired
    private TariffDao tariffDao;

    // добавляем тарифф
    @Transactional
    public void addTariff() {

        Tariff tariff = new Tariff();
        tariff.setName( "Maximum" );
        tariff.setPrice( 250 );

        tariffDao.add( tariff );
    }

    // перенести сюда действия из TariffDaoTest !!!
}
