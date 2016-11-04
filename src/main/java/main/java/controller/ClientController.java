package main.java.controller;

import main.java.entity.Client;
import main.java.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping( "/ClientByID" )
    public Client ClientByID ( @PathVariable("id") int id ){

        Client client = clientService.getById( id );
        return client;
    }
}
