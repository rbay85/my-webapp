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

    @RequestMapping( value = "/", method = RequestMethod.GET )
    public String index( ){ return "index"; }

    @RequestMapping( value = "/clientById", method = RequestMethod.GET )
    public String clientById( @RequestParam( value = "id", required = false ) String id, Model model ){

        try{
            int clientId = new Integer( id );

            model.addAttribute( clientService.getById( clientId ));
        } catch ( IllegalArgumentException e ){
            model.addAttribute( "error", "client not found" );
        }
        return "clientById";
    }

    @RequestMapping( value = "showAllClients", method = RequestMethod.GET )
    public String showAllClients( Model model ){

        model.addAttribute( "clientList", clientService.getAll() );
        return "showAllClients";
    }

    @RequestMapping( value = "addClient", method = RequestMethod.GET )
    public String addClient ( @RequestParam( value = "firstName", required = false ) String firstName,
                              @RequestParam( value = "lastName", required = false ) String lastName,
                              @RequestParam( value = "birthDay", required = false ) String birthDay,
                              @RequestParam( value = "passport", required = false ) String passport,
                              @RequestParam( value = "address", required = false ) String address,
                              @RequestParam( value = "email", required = false ) String email,
                              @RequestParam( value = "password", required = false ) String password,
                              Model model ){

        if ( firstName.equals( "" ) || lastName.equals( "" ) || passport.equals( "" ) || birthDay.equals( "" ) || password.equals( "" ) ){
            model.addAttribute( "error", "error: Fill in all * fields, please !" );
        } else {
            try {
                clientService.addClient( firstName,
                                         lastName,
                                         birthDay,
                                         passport,
                                         address,
                                         email,
                                         password);
                model.addAttribute( "message", "New client was successfully added" );
            } catch (  ConstraintViolationException e ){
                model.addAttribute( "error", "error: Please, input valid e-mail !" );
            }
        }
        return "addClient";
    }
}
