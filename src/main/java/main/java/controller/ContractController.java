package main.java.controller;

import main.java.service.ClientService;
import main.java.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.NoResultException;


@Controller
public class ContractController {

    @Autowired
    private ContractService contractService;

    @Autowired
    private ClientService clientService;

    // вывод списка контрактов
    @RequestMapping( value = "contract", method = RequestMethod.GET )
    public String showAllContracts( Model model ){

        model.addAttribute( "contractList", contractService.getAllContracts() );
        return "contract";
    }

    // поиск контракта по телефону
    @RequestMapping( value = "/contractByPhone", method = RequestMethod.GET )
    public String contractByPhone( @RequestParam( value = "phone", required = false ) String phone, Model model ){

        try {
            model.addAttribute( contractService.getByPhone( phone ));
        } catch ( NoResultException e) {
            model.addAttribute( "error", " contract not found" );
        }
        return "contractByPhone";
    }

    // блокировка/разблокировка админом
    @RequestMapping( value = "/lockUnlock", method = RequestMethod.GET )
    public String lockUnlock ( @RequestParam( value = "phone", required = false ) String phone,
                               @RequestParam( value = "condition", required = false ) String condition,
                               Model model ){

        try{
            model.addAttribute( "message", contractService.adminLock( phone, condition ));
        } catch ( NullPointerException e ) {
            model.addAttribute( "error", " Choose an action, please! " );
        } catch ( NoResultException e ) {
            model.addAttribute( "error", " Fill in the field properly, please! " );
        }
        return "lockUnlock";
    }

    // блокировка/разблокировка клиентом
    @RequestMapping( value = "/lockUnlock1", method = RequestMethod.GET )
    public String lockUnlock1 ( @RequestParam( value = "contractId", required = false ) String contractId,
                               @RequestParam( value = "condition", required = false ) String condition,
                               Model model ){

        int clientId = 6;
        try {
            model.addAttribute(clientService.getById(clientId));
            int id = new Integer(contractId);
            String message = contractService.clientLock(id, condition);
            model.addAttribute("message", message);
        } catch ( NumberFormatException e ){
            model.addAttribute( "error", " " );
        } catch ( NullPointerException e ) {
            model.addAttribute( "error", " choose an action, please! " );
        } catch ( NoResultException e ) {
            model.addAttribute( "error", " NoResultException " );
        }
        return "lockUnlock1";
    }
}