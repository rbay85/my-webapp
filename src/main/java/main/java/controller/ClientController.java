package main.java.controller;


import main.java.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    // добавляем нового клиента
    @RequestMapping( value = "addClient", method = RequestMethod.GET )
    public String addClient ( @RequestParam( value = "firstName", required = false ) String firstName,
                              @RequestParam( value = "lastName",  required = false ) String lastName,
                              @RequestParam( value = "birthDay",  required = false ) String birthDay,
                              @RequestParam( value = "passNo",    required = false ) String passNo,
                              @RequestParam( value = "address",   required = false ) String address,
                              Model model ){

            if ( "".equals( firstName ) ||
                 "".equals( lastName ) ||
                 "".equals( birthDay ) ||
                 "".equals( passNo ) ||
                 "".equals( address ) ){

                model.addAttribute( "error", "fill in all fields" );
            } else {
                model.addAttribute(
                        "message",
                        clientService.addClient(
                                firstName,
                                lastName,
                                birthDay,
                                passNo,
                                address));
            }

        return "redirect:/client";
    }

    // добавляем юзера в клиент
    @RequestMapping( value = "addUserInClient", method = RequestMethod.GET )
    public String addUserInClient ( @RequestParam( value = "clientId", required = false ) String clientId,
                                    @RequestParam( value = "email",    required = false ) String email,
                                    @RequestParam( value = "role",     required = false ) String role,
                                    Model model ){
        try {
            if ( role == null ) {
                role = "";
            }
            model.addAttribute( "message", clientService.addUserInClient( clientId, email, role ));
        } catch (  ConstraintViolationException e ){
            model.addAttribute( "error", "please, input valid e-mail" );
        }
        return "redirect:/client";
    }


//------- что далее НЕ связано с клиентом !!! -----------------------------------------------------------------------------------------------

    @RequestMapping( value = "/", method = RequestMethod.GET )
    public String index( ){ return "index"; }

    @RequestMapping( value = "login", method = RequestMethod.GET )
    public String login( ){ return "/login"; }

    @RequestMapping( value = "403", method = RequestMethod.GET )
    public String e403( ){ return "/403"; }

    @RequestMapping( value = "j_spring_security_logout", method = RequestMethod.GET )
    public String logout( HttpServletRequest request, HttpServletResponse response ){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout( request, response, auth );
        }
        return "/login";
    }
}
