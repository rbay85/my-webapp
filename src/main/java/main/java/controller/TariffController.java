package main.java.controller;

import main.java.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.PersistenceException;


@Controller
public class TariffController {

    @Autowired
    private TariffService tariffService;

    // вывод списка тарифов
    @RequestMapping( value = "tariff", method = RequestMethod.GET )
    public String showAllTariffs( Model model ){

        model.addAttribute( "tariffList", tariffService.getAllTariffs() );
        model.addAttribute( "optionList", tariffService.getAllOptions() );

        return "tariff";
    }

    // добавление тарифа
    @RequestMapping( value = "/addTariff", method = RequestMethod.GET )
    public String addTariff ( @RequestParam( value = "name",  required = false ) String name,
                              @RequestParam( value = "price", required = false ) String price,
                              Model model ){

        try{
            if ( name != null ){
                tariffService.add( name, price );
                model.addAttribute( "message", " new tariff successfully added" );
            } else {
                model.addAttribute( "message", " tariff name must not be null!" );
            }
        } catch ( NumberFormatException e ) {
            model.addAttribute( "error", " price must be a number !" );
        } catch ( PersistenceException e ) {
            model.addAttribute( "error", " tariff with the same name already exists !" );
        }
        return "redirect:/tariff";
    }

    // удаление тарифа
    @RequestMapping( value = "/deleteTariff", method = RequestMethod.GET )
    public String deleteTariff ( @RequestParam( value = "id", required = false ) String id,
                              Model model ){

        try{
            String message = tariffService.delete( id );
            model.addAttribute( "message", message );
        } catch ( NumberFormatException e ) {
            model.addAttribute( "error", " NumberFormatException " );
        }
        return "redirect:/tariff";
    }

    // удаление опции
    @RequestMapping( value = "/deleteOptionFromTariff", method = RequestMethod.GET )
    public String deleteOptionFromTariff ( @RequestParam( value = "tariffId", required = false ) String tariffId,
                                           @RequestParam( value = "optionId", required = false ) String optionId,
                                           Model model ){

        try{
            String message = tariffService.deleteOption( tariffId, optionId );
            model.addAttribute( "message", message );
        } catch ( NumberFormatException e ) {
            model.addAttribute( "error", " NumberFormatException " );
        }
        return "redirect:/tariff";
    }

    // управление опциями в тарифе
    @RequestMapping( value = "/addOptionInTariff", method = RequestMethod.GET )
    public String addOptionInTariff ( @RequestParam( value = "tariffId", required = false ) String tariffId,
                                      @RequestParam( value = "optionId", required = false ) String optionId,
                                      Model model ){

        try{
            String massage = tariffService.addOptionInTariff( tariffId, optionId );
            model.addAttribute( "message", massage );
        } catch ( NumberFormatException e ) {
            model.addAttribute( "error", " Choose a tariff from the table " );
        }
        return "redirect:/tariff";
    }
}


