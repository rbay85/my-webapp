package main.java.service;

import com.sun.jersey.spi.inject.Inject;
import main.java.dao.RestTariffDao;
import main.java.dao.TariffDao;
import main.java.entity.Contract;
import main.java.entity.Tariff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

//@Service
@Path( "/tariff" )
public class RestService {

    //@Autowired
    // private TariffDao tariffDao = new TariffDao();

    //@Inject
    //private RestTariffDao restTariffDao;

    private RestTariffDao restTariffDao = new RestTariffDao();

    // возвращаем тариф по Id
    @Transactional
    @GET
    @Path( "/{tariffId}" )
    @Produces( MediaType.APPLICATION_JSON )
    public List<Contract> getTariff(@PathParam( "tariffId" ) String tariffId ) {

        return restTariffDao.get( Integer.parseInt( tariffId )).getContractList();
    }


    // принимает объект в виде JSON, возвращает новый объект с таким же именем
//    @POST
//    @Path( "/post" )
//    @Consumes( MediaType.APPLICATION_JSON )
//    @Produces( MediaType.APPLICATION_JSON )
//    public Car patchCar( Car car ) {
//        return new Car( car.getModel());
//    }


}
