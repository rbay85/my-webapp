package main.java.controller;

import com.sun.jersey.spi.inject.Inject;
import main.java.dao.RestTariffDao;
import main.java.dao.TariffDao;
import main.java.entity.Tariff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


//@Path( "/tariff" )
@RestController
public class RestService {

    @Autowired
    private TariffDao restTariffDao;

    //@Inject
    //private RestTariffDao restTariffDao;

//    private RestTariffDao restTariffDao = new RestTariffDao();

    // возвращаем тариф по Id
//    @Transactional
//    @GET
//    @Path( "/{tariffId}" )
//    @Produces( MediaType.APPLICATION_JSON )
    @RequestMapping("/json/tariff/{tariffId}")
    public Tariff getTariff(@PathVariable String tariffId ) {
        return restTariffDao.get(Integer.parseInt(tariffId));
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
