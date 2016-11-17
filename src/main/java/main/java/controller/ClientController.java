package main.java.controller;


import main.java.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.ConstraintViolationException;


@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    // выводим всех клиентов
    @RequestMapping( value = "client", method = RequestMethod.GET )
    public String client( Model model ){

        model.addAttribute( "clientList", clientService.getAllClients() );
        return "client";
    }

    @RequestMapping( value = "addClient", method = RequestMethod.GET )
    public String addClient ( @RequestParam( value = "firstName", required = false ) String firstName,
                              @RequestParam( value = "lastName",  required = false ) String lastName,
                              @RequestParam( value = "birthDay",  required = false ) String birthDay,
                              @RequestParam( value = "passNo",    required = false ) String passNo,
                              @RequestParam( value = "address",   required = false ) String address,
                              Model model ){
        try {
            if ( firstName.equals( "" ) ||
                 lastName.equals( "" ) ||
                 birthDay.equals( "" ) ||
                 passNo.equals( "" ) ||
                 address.equals( "" ) ){

                model.addAttribute( "error", "fill in all fields" );
            } else {
                model.addAttribute(
                        "message",
                        clientService.addClient(
                                firstName,
                                lastName,
                                birthDay,
                                passNo,
                                address
                        )
                );
            }
        } catch (  NullPointerException e ) {
            model.addAttribute("error", " ");
        } catch (  ConstraintViolationException e ){
            model.addAttribute( "error", "error: Please, input valid e-mail !" );
        }
        return "redirect:/client";
    }

    // поиск клиента по id
    @RequestMapping( value = "clientById", method = RequestMethod.GET )
    public String clientById( @RequestParam( value = "id", required = false ) String id, Model model ){

        try{
            int clientId = new Integer( id );

            model.addAttribute( clientService.getById( clientId ));
        } catch ( IllegalArgumentException e ){
            model.addAttribute( "error", "client not found" );
        }
        return "clientById";
    }

    // что далее НЕ связано с клиентом !!!
    @RequestMapping( value = "/", method = RequestMethod.GET )
    public String index( ){ return "index"; }

    @RequestMapping( value = "login", method = RequestMethod.GET )
    public String login( ){ return "/login"; }
}
