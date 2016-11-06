package main.java.controller;

import main.java.entity.Client;
import main.java.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping( value = "/clientById", method = RequestMethod.GET )
    public String clientById( @RequestParam( value = "id", required = false ) String id, Model model ){

        try{
            int clientId = new Integer( id );

            Client client = clientService.getById( clientId );
            model.addAttribute( client );
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
}
