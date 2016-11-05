package main.java.controller;

import main.java.entity.Client;
import main.java.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping( value = "/clientById", method = RequestMethod.GET )
    public String clientById (@RequestParam String id, Model model ){

        if ( id == null ) {
            model.addAttribute( "client", " " );
        }
        else {
            try{
                int clientId = new Integer( id );

                Client client = clientService.getById( clientId );
                model.addAttribute( client );
            } catch ( IllegalArgumentException e ){
                model.addAttribute( "error", "client not found" );
            }
        }
        return "clientById";
    }

    public String showAllClients ( Model model ){

        List<Client> clientList = clientService.getAll();
        model.addAttribute( "clientList", );
        return "showAllClients";
    }
}
