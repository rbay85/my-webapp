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

    @RequestMapping( value = "tariff", method = RequestMethod.GET )
    public String showAllClients( Model model ){

        model.addAttribute( "tariffList", tariffService.getAll() );
        return "tariff";
    }

    @RequestMapping( value = "/addTariff", method = RequestMethod.GET )
    public String addTariff ( @RequestParam( value = "name", required = false ) String name,
                              @RequestParam( value = "price", required = false ) String price,
                              Model model ){

        try{
            if ( name != null ){
                tariffService.addTariff( name, price );
                model.addAttribute( "message", " New tariff was successfully added" );
            } else {
                model.addAttribute( "message", " tariff name must not be null!" );
            }
        } catch ( NumberFormatException e ) {
            model.addAttribute( "error", " price must be a double !" );
        } catch ( PersistenceException e ) {
            model.addAttribute( "error", " tariff with the same name already exists !" );
        }
        return "redirect:/tariff";
    }

    @RequestMapping( value = "/deleteTariff", method = RequestMethod.GET )
    public String delete ( @RequestParam( value = "id", required = false ) String id,
                              Model model ){

        try{
            tariffService.delete( id );
        } catch ( NullPointerException e ) {
            model.addAttribute( "error", " NullPointerException " );
        } catch ( NumberFormatException e ) {
            model.addAttribute( "error", " NumberFormatException " );
        }
        return "redirect:/tariff";
    }

    @RequestMapping( value = "/addOptionInTariff", method = RequestMethod.GET )
    public String addOptionInTariff ( @RequestParam( value = "tariffId", required = false ) String tariffId,
                                      @RequestParam( value = "optionId", required = false ) String optionId,
                                      Model model ){

        try{



        } catch ( NullPointerException e ) {
            model.addAttribute( "error", " NullPointerException " );
        } catch ( NumberFormatException e ) {
            model.addAttribute( "error", " NumberFormatException " );
        }
        return "redirect:/tariff";
    }
}


