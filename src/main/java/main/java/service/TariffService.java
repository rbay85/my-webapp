package main.java.service;

import main.java.dao.OptionDao;
import main.java.dao.TariffDao;
import main.java.entity.Tariff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TariffService {

    @Autowired
    private TariffDao tariffDao;

    @Autowired
    private OptionDao optionDao;

    // выводим все тарифы
    @Transactional
    public List<Tariff> getAll() {

        List<Tariff> tariffList = tariffDao.getAll();
        return tariffList;
    }

    // добавляем тарифф
    @Transactional
    public void add( String name, String price) {

        Tariff tariff = new Tariff();
        tariff.setName( name );
        tariff.setPrice( Double.parseDouble( price ));

        tariffDao.add( tariff );
    }

    // удаляем тарифф по Id
    @Transactional
    public void delete( String id ) {

        tariffDao.delete( Integer.parseInt( id ) );
    }

    @Transactional
    public void addOptionInTariff( String tariffId, String optionId ) {

        Tariff tariff = tariffDao.get( Integer.parseInt( tariffId ));
        tariff.getOptionList().add( optionDao.get( Integer.parseInt( optionId )));
        tariffDao.update( tariff );
    }
}
