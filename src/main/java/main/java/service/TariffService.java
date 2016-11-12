package main.java.service;

import main.java.dao.OptionDao;
import main.java.dao.TariffDao;
import main.java.entity.Option;
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
    public List<Tariff> getAllTariffs() {

        List<Tariff> tariffList = tariffDao.getAll();
        return tariffList;
    }

    // выводим все опции
    @Transactional
    public List<Option> getAllOptions() {

        List<Option> optionList = optionDao.getAll();
        return optionList;
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
    public String delete( String id ) {

        tariffDao.delete( Integer.parseInt( id ) );
        return "tariff successfully deleted";
    }

    // удаляем опцию из тарифа
    @Transactional
    public String deleteOption( String tariffId, String optionId ){

        Tariff tariff = tariffDao.get( Integer.parseInt( tariffId ));
        Option option = optionDao.get( Integer.parseInt( optionId ));

        tariff.getOptionList().remove( option );

        tariffDao.update( tariff );
        return "option successfully deleted from tariff";
    }

    // добавляем опцию в тариф
    @Transactional
    public String addOptionInTariff( String tariffId, String optionId ) {

        String message;

        if (!optionId.equals( "0" )){
            Tariff tariff = tariffDao.get( Integer.parseInt( tariffId ));
            Option option = optionDao.get( Integer.parseInt( optionId ));

            if ( tariff.getOptionList().contains( option )){
                message = "the tariff already contains this option!";
            } else {
                tariff.getOptionList().add( option );
                tariffDao.update( tariff );
                message = "the option successfully added in the tariff";
            }
        } else {
            message = "choose an option in dropdown!";
        }
        return message;
    }
}