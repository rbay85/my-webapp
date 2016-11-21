package main.java.controller;

import main.java.dao.TariffDao;
import main.java.dto.DtoClient;
import main.java.dto.DtoTariff;
import main.java.entity.Client;
import main.java.entity.Contract;
import main.java.entity.Tariff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class RestService {

    @Autowired
    private TariffDao tariffDao;

    private List<DtoTariff> dtoTariffList = new ArrayList<DtoTariff>();

    private List<DtoClient> dtoClientList = new ArrayList<DtoClient>();

    // возвращает список клиентов
    @RequestMapping( "/json/tariffs" )
    public List<DtoTariff> getTariffList() {

        dtoTariffList.clear();

        List<Tariff> tariffList = tariffDao.getAll();

        for ( Tariff tariff : tariffList ){

            DtoTariff dtoTariff = new DtoTariff();

            dtoTariff.setId( Integer.toString( tariff.getId() ));
            dtoTariff.setName( tariff.getName() );

            dtoTariffList.add( dtoTariff );
        }
        return dtoTariffList ;

    }

    // возвращает список клиентов использующих этот тариф
    @RequestMapping( "/json/tariff/{tariffId}" )
    public List<DtoClient> getClientsUsingTariff( @PathVariable String tariffId ) {

        dtoClientList.clear();

        List<Contract> contractList = tariffDao.get( Integer.parseInt( tariffId )).getContractList();

        for ( Contract contract : contractList ){

            DtoClient dtoClient = new DtoClient();
            Client client = contract.getClient();

            dtoClient.setFirstName( client.getFirstName() );
            dtoClient.setLastName( client.getLastName() );
            dtoClient.setBirthDay( client.getBirthDay() );

            dtoClientList.add( dtoClient );
        }
        return dtoClientList;
    }
}